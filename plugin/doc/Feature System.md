# Feature System

Fyre uses a feature-based system to independently load components and provide a
graceful fallback over versions. Although Fyre is not explicitly designed with
previous versions in mind, (particularly with balance) due to the nature of
fyre's features, they may be used for other plugins.

Modular features also makes development easier, since each component is
independent (while still allowing dependencies), and issues with one section
is easier to find, than with a monolithic setup.

![Feature System Overview](./img/feature-system-overview.drawio.png)

Features can have dependencies for other features, and have dependencies
relating to specific minecraft minimum versions. Features also support libraries
that other features can use, this is done with a shell feature. For example:

```java
/**
 * Feature wrapper for {@link TagDataHolder}
 * <hr>
 * Represents any {@link Entity} that may have external data serialized to their
 * 'Tag' nbt value.
 */
public class TagDataHolderFeature extends Feature {

	public static MinecraftVersion minVersion = NBTAdapter.minVersion;

	protected boolean enabled = false;
	protected static String name = "NBT TagDataHolder";
	private static TagDataHolderFeature instance;

	public static TagDataHolderFeature getInstance() {
		if (instance == null) {
			instance = new TagDataHolderFeature();
		}
		return instance;
	}

	@Override
	public MinecraftVersion getMinecraftVersion() {
		return minVersion;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public boolean setup(FyrePlugin plugin) {
		enabled = NBTAdapter.getInstance().isEnabled();
		return enabled;
	}

	@Override
	public String getName() {
		return name;
	}

}
```

This feature depends on NBTAdapter to load, and only enables if that requirement
is active. As an example, the outline for the
[BoatInventories](https://github.com/the-dwellers/Fyre/blob/master/plugin/src/main/java/io/github/the_dwellers/fyreplugin/features/BoatInventories.java)
feature:

![BoatInventories Dependency Chain](./img/feature-boatinventories-outline.drawio.png)

## Features and Dependencies

Graph of features and dependencies as of 14-June-2020

![Feature Dependency Graph](./img/feature-dependencies.drawio.png)

| Feature              | Minimum Dependency   | Description                                                         | Comment                                                      |
| -------------------- | -------------------- | ------------------------------------------------------------------- | ------------------------------------------------------------ |
| Development          | Any                  | Development features (not included in release)                      |                                                              |
| NBTAdapter           | MC 1.13.2            | NBT functions such as saving, loading, generating chat text, etc... | Heavy integration with `net.minecraft.server`                |
| TagDataHolderFeature | NBTAdapter           | Store arbitrary data in entity nbt                                  |                                                              |
| TagInventoryFeature  | TagDataHolderFeature | Store inventories in entity tags                                    |                                                              |
| BoatInventories      | TagInventoryFeature  | Open boats like chests                                              |                                                              |
| Advancements         | Any                  | Advancement Progression                                             |                                                              |
| AIFixes              | Any                  | AI Bugfixes and improvements                                        |                                                              |
| ChatManagerFeature   | NBTAdapter           | Chat formatting                                                     |                                                              |
| ClientBreakItem      | MC 1.14.4            | Client tool break                                                   | Why is this not in the api‽                                  |
| BloodMoon            | Any                  | Blood moon event with harder and more mobs                          |                                                              |
| EntityAttributes     | Any                  | Entity Value changes                                                |                                                              |
| ItemFeatures         | Any                  | Functionality relating to items                                     |                                                              |
| LandTrampling        | Any                  | Trample grass and crops into dirt                                   |                                                              |
| Management           | Any                  | Server management utilities                                         |                                                              |
| Merchants            | MC 1.14.4            | Trade with NPCs and unlock levels                                   |                                                              |
| PlantHoeHarvest      | MC 1.14.4            | Right-click to harvest crops                                        | Soft depend on ClientBreakItem to provide visual effects     |
| Compost              | MC 1.14.4            | Compost extra items                                                 |                                                              |
| Rainlevel            | MC 1.16.5            | Set rainLevel to custom values                                      | Requires server patch. Large levels cause severe client lag. |

*('Any' represents any version supported by Fyre. The current smallest supported version is MC 1.13.2)*
