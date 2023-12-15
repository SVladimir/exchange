
# Trading Application

## Description
This Trading Application is a comprehensive solution designed for simulating and processing financial trades. It features components for generating trade requests, processing them, and managing different trading entities like bonds, sectors, and traders.

## Key Components
- **TradeRequestGenerator**: Responsible for generating trade requests in a simulated trading environment.
- **TradeRequestProcessor**: Handles the processing of trade requests.
- **TradeRequestQueue**: Manages a queue of trade requests, ensuring thread-safe operations.
- **TradeRunner**: Orchestrates the trading process and coordinates various activities.
- **TradingService and TradingServiceImp**: Core components for executing trades and managing portfolios.
- **Model Classes (Bond, Exchange, Sector, Trader, TradeRequest)**: Represent various entities in the trading system.

## Prerequisites
- Java 11 or later.
- Maven (for building and managing dependencies).
