package com.svs.exchange.model;

import com.svs.exchange.TradeRequestGenerator;
import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class Exchange {
    private final List<Bond> bonds;
    private final List<Sector> sectors;
    private final List<Trader> traders;
    @Value("${app.bonds-file-path}")
    private String bondsFilePath;

    @Value("${app.sectors-file-path}")
    private String sectorsFilePath;

    @Value("${app.traders-file-path}")
    private String tradersFilePath;
    private static final Logger logger = LoggerFactory.getLogger(Exchange.class);


    public Exchange() {
        bonds = new CopyOnWriteArrayList<>();
        sectors = new CopyOnWriteArrayList<>();
        traders = new CopyOnWriteArrayList<>();
    }
    public enum DataType {
        BOND, TRADER, SECTOR
    }
    public void addBond(String bondName, double price) {
        bonds.add(new Bond(bondName, price));
    }

    public void addSector(String sectorName, double limit) {
        sectors.add(new Sector(sectorName, limit));
    }

    public void addTrader(String traderName, double portfolioValue) {
        traders.add(new Trader(traderName, portfolioValue));
    }

    public Trader getRandomTrader() {
        if (traders.isEmpty()) {
            return null;
        }
        // Get a random index
        int randomIndex = new Random().nextInt(traders.size());
        // Return the random element
        return traders.get(randomIndex);
    }

    public Bond getRandomBond() {
        if (bonds.isEmpty()) {
            return null;
        }
        // Get a random index
        int randomIndex = new Random().nextInt(bonds.size());
        // Return the random element
        return bonds.get(randomIndex);
    }

    public Sector getRandomSector() {
        if (sectors.isEmpty()) {
            return null;
        }
        // Get a random index
        int randomIndex = new Random().nextInt(sectors.size());
        // Return the random element
        return sectors.get(randomIndex);
    }

    private Trader getTraderByName(String traderName) {
        for (Trader trader : traders) {
            if (trader.getName().equals(traderName)) {
                return trader;
            }
        }
        throw new IllegalArgumentException("Trader not found: " + traderName);
    }
    @PostConstruct
    public void init() {
        loadData(bondsFilePath, DataType.BOND);
        loadData(sectorsFilePath, DataType.SECTOR);
        loadData(tradersFilePath, DataType.TRADER);
    }
    public void loadData(String filePath, DataType dataType) {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
            for (CSVRecord record : csvParser) {
                String name = record.get(0);
                double value = Double.parseDouble(record.get(1));

                switch (dataType) {
                    case BOND:
                        addBond(name, value);
                        break;
                    case TRADER:
                        addTrader(name, value);
                        break;
                    case SECTOR:
                        addSector(name, value);
                        break;
                }
            }
        } catch (IOException e) {
            logger.error("Error open file {}: {}", filePath, e.getMessage(), e);

        }
    }
}