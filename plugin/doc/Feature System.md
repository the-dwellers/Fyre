# Feature System

Fyre uses a feature-based system to independently load components and provide a
graceful fallback over versions. Although Fyre is not explicitly designed with
previous versions in mind, (particularly with balance) due to the nature of
fyre's features, they may be used for other plugins.

Modular features also makes development easier, since each component is
independent (while still allowing dependencies), and issues with one section
is easier to find, than with a monolithic setup.

![Feature System Overview](E:\Development\Gits\fyre\plugin\doc\img\feature-system-overview.drawio.png)
