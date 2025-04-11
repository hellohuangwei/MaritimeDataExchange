// SPDX-License-Identifier: MIT
pragma solidity >=0.7.0 <0.9.0;

import "./MaritimeDataMarketCore.sol";

/// @title MaritimeDataRouter
/// @notice A decentralized smart contract for scheduling, publishing, and matching maritime data exchange orders,
///         including AIS, port ETA, satellite imagery, cargo manifests, or vessel energy reports.
contract MaritimeDataRouter is MaritimeDataMarketCore(msg.sender) {

    bool isStopped = false;

    /// @notice Set the address that will receive protocol-level transaction fees.
    ///         Typically this could be the port authority, data broker, or infrastructure maintainer.
    /// @param _feeTo Address to receive accumulated exchange fees
    function setFeeTo(address _feeTo) external {
        require(msg.sender == feeToSetter, 'cyberpop: FORBIDDEN');
        feeTo = _feeTo;
    }

    /// @notice Modifier that disables certain functions in emergency mode,
    ///         such as during system breach or maritime cybersecurity alert.
    modifier stoppedInEmergency {
        require(isStopped == false, "this contract entered emergency mode");
        _;
    }

    /// @notice Toggle the emergency switch for the data exchange system.
    ///         Can be used by administrators (e.g., coastguard or digital regulators) in case of security threats.
    /// @param switch_ `true` to activate emergency stop, `false` to resume operation
    function setIsStopped(bool switch_) external {
        require(msg.sender == feeToSetter, 'cyberpop: FORBIDDEN');
        isStopped = switch_;
    }

    /**
     * @notice Submit a new maritime data order for publishing or requesting marine data,
     *         such as real-time AIS streams, port arrival forecasts, or vessel performance datasets.
     * @param maker           Address of the ship operator, port, or maritime data provider
     * @param salt            Random nonce to guarantee uniqueness of the order
     * @param listingTime     UNIX timestamp when the data offer/request goes live
     * @param expirationTime  UNIX timestamp when the data becomes obsolete or inaccessible
     * @param offer           `true` if offering data; `false` if requesting data
     * @param assetData       Encoded description of tokens or credits used for exchange (e.g., CO2 tokens)
     * @param callData        Encoded instructions for data retrieval, like IPFS path or on-chain gateway call
     * @param orderData       Metadata describing the dataset type (e.g., "Satellite", "ETA", "EnergyReport")
     * @param signature       Cryptographic signature to prove the order's authenticity
     */
    function submitMaritimeOrder(  
        address maker, 
        uint256 salt,
        uint listingTime,
        uint expirationTime,
        bool offer,
        bytes memory assetData, 
        bytes memory callData, 
        bytes memory orderData,
        bytes calldata signature
    ) external {
        MaritimeDataMarketCore.commitOrder(
            Order(maker, salt, listingTime, expirationTime, offer),
            assetData, callData, orderData, signature
        );  
    }

    /**
     * @notice Cancel an existing maritime data order.
     *         Commonly used if vessel schedules change or port conditions render the data irrelevant.
     * @param orderHash The keccak256 hash of the order being canceled
     */
    function revokeMaritimeOrder(bytes32 orderHash) external {
        MaritimeDataMarketCore.cancelOrder_(orderHash);
    }

    /**
     * @notice Match and execute multiple maritime data exchange orders simultaneously,
     *         enabling automated trade between ships, ports, satellite agencies, and offshore data nodes.
     * @param hashOrders  Encoded hashes representing matched data exchange orders
     * @param signers     Encoded public keys or authorized wallets for verification
     * @param callDatas   Instructions to invoke data retrieval methods or delivery services
     * @param callTarget  Encoded addresses (e.g., IPFS relays, oracles, or cloud gateways)
     * @param signatures  Collection of user signatures that approve each order
     * @param orderData   Dataset type tags or marine context metadata per matched order
     */
    function matchMaritimeOrders(
        bytes memory hashOrders,
        bytes memory signers,
        bytes memory callDatas,
        bytes memory callTarget,
        bytes memory signatures,
        bytes memory orderData
    ) external stoppedInEmergency {
        MaritimeDataMarketCore.orderMatch_(
            hashOrders, signers, callDatas, callTarget, signatures, orderData
        );    
    }

}
