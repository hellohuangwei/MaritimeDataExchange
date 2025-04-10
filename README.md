
# MaritimeDataRouter Contract

`MaritimeDataRouter` is a smart contract designed specifically for **maritime data exchange** scenarios. It aims to build a decentralized ship-to-shore data sharing marketplace. The contract enables a wide range of participants such as **vessels, ports, shipping companies, and data service providers** to submit, match, and cancel encrypted data trading orders, enabling trusted maritime data exchange across organizations and regions.

> ⚠️ This router contract relies on the base contract `MaritimeDataMarketCore`, which must be deployed before this contract can function.

## Background & Value

The maritime industry currently faces several challenges:

- Vessel-generated data is diverse and isolated, making unified access difficult
- Lack of trusted coordination mechanisms between ports, relying heavily on manual communication
- Data providers struggle to securely distribute and monetize their data using encryption
- Absence of verifiable on-chain order protocols and arbitration mechanisms

This contract serves as the **core router** for a decentralized maritime data marketplace protocol. It supports multi-party data listings, matching, cryptographic validation, and fee distribution, laying the foundation for a **cross-border, cross-platform intelligent shipping collaboration network**.

## Quick Start

### Prerequisites

- Node.js v16+
- Hardhat
- MetaMask or any Ethereum-compatible wallet
- Solidity version: >=0.7.0 <0.9.0

### 1. Clone the repository

```bash
git clone https://github.com/your-org/maritime-data-exchange.git
cd maritime-data-exchange
```

### 2. Install dependencies

```bash
npm install
```

## Deployment Guide

1. Compile the contract:

```bash
npx hardhat compile
```

2. Start a local testnet:

```bash
npx hardhat node
```

3. Deploy the contract:

```bash
npx hardhat run scripts/deploy.js --network localhost
```

Example deployment script (`scripts/deploy.js`):

```javascript
async function main() {
  const [deployer] = await ethers.getSigners();

  console.log("Deploying contracts with:", deployer.address);

  const MarketCore = await ethers.getContractFactory("MaritimeDataMarketCore");
  const marketCore = await MarketCore.deploy();
  await marketCore.deployed();

  const Router = await ethers.getContractFactory("MaritimeDataRouter");
  const router = await Router.deploy();
  await router.deployed();

  console.log("MaritimeDataRouter deployed to:", router.address);
}

main().catch((error) => {
  console.error(error);
  process.exitCode = 1;
});
```

## Contract Functions Overview

- `submitMaritimeOrder`: Submit an encrypted maritime data order (either offering or requesting)
- `cancelMaritimeOrder`: Cancel an unmatched data order
- `matchMaritimeOrders`: Match two orders, verify signatures, and perform data/asset transfer
- `setFeeTo`: Set the address to receive platform transaction fees

## Test Cases

Run the following command to execute tests:

```bash
npx hardhat test
```

Example test file (`test/MaritimeDataRouter.test.js`):

```javascript
const { expect } = require("chai");

describe("MaritimeDataRouter", function () {
  let router, owner, addr1;

  beforeEach(async function () {
    [owner, addr1] = await ethers.getSigners();

    const MarketCore = await ethers.getContractFactory("MaritimeDataMarketCore");
    const core = await MarketCore.deploy();
    await core.deployed();

    const Router = await ethers.getContractFactory("MaritimeDataRouter");
    router = await Router.deploy();
    await router.deployed();
  });

  it("Should allow the owner to set a fee receiver", async function () {
    await router.setFeeTo(addr1.address);
    expect(await router.feeTo()).to.equal(addr1.address);
  });

  it("Should allow order submission", async function () {
    const order = {
      maker: addr1.address,
      salt: 1234,
      listingTime: Math.floor(Date.now() / 1000),
      expirationTime: Math.floor(Date.now() / 1000) + 3600,
      offer: true,
    };

    await expect(
      router.connect(addr1).submitMaritimeOrder(
        order.maker,
        order.salt,
        order.listingTime,
        order.expirationTime,
        order.offer,
        "0x", "0x", "0x", "0x"
      )
    ).to.not.be.reverted;
  });
});
```

## Example Use Cases

- **Port-to-port collaboration**: Real-time tracking of cargo flow between ports (e.g., Ningbo Port ↔ Singapore Port)
- **Vessel scheduling and weather data**: Chain-based verification and pricing using AIS and meteorological data
- **Satellite data leasing**: Providers offer encrypted access with permission controls and automated payment
- **Logistics order matching**: Forwarders can submit and match orders through smart contract automation
