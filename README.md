# CreativerseMC-Plugin
Minecraft 1.18.1 Java Spigot/Paper Plugin

# Dependencies to run on server
WorldEdit, AsyncWorldEdit, and PlotSquared plugins must be installed on a 1.18.1 server.
On top of that, the plugin needs to be connected to an IPFS node and an Ethereum node. I recommend running a local IPFS node and using [Alchemy](https://www.alchemy.com/) for the Ethereum node, both are free and easy to set up.

# Dependencies to build from source:
- [PlotSquared v6](https://github.com/IntellectualSites/PlotSquared)
- [WorldEdit](https://github.com/EngineHub/WorldEdit)
- [Web3J](https://github.com/web3j/web3j) core & contracts
- org.json
- xerial.ewlite.jdbc
- Spigot API 1.18.1

## Note if you are building from source:
The assets folder that contains the textures for Minecraft need to be included in the src folder for 3D files to be created. You can get the assets folder from the Minecraft jar file (Found in .minecraft/versions folder)
