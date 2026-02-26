# Changelog


## 0.1.0
### General
- Re-implement the item durability hooks, so mana items will have durability based on the mana they contain
- Implement a specific render hook for other entities holding the vitreous pickaxe
- Implement the GOG sky renderer
- Implement Baubles keybind
### Fixes
- The config not properly being loaded
- Solid vines not being climbable
- Mana bottle placed full water blocks, and could cause a NPE
- Somehow I forgot the Minecraft Forge License
- Corporea request keybind now works once per key press
- Fix constant openGL errors in the lexica botania
- Many recipes used the "old" item instead of the BTW version 
  - Red string
  - Fel Pumpkin
  - Jump boost brew
  - Rune of Spring
  - Crop transmutation
  - Many decorative blocks
### Balancing
- Reduce price of the flower pouch to use substantially less wool
- Most things that were previously using wool blocks now use knit or unnknit wool items
- Remove the wool → string recipe
- Make the spectrolus use unnknit wool instead of wool blocks (was prohibitively expensive lol)
### API
- Implement the mod IMC event for items that should be blacklisted from being picked up by the magnet
  - See IMCHandler for more info on how to implement this. 
  - It needs to be done sometime in the intialization phase.

## 0.0.4
### General
- Reimplement the config (using the new config system)
- Implement chest gen hooks 
  - This means that botania items show up in dungeon chests now
- Add auto publishing to modrinth (this should reduce the amount of issues with missing dependencies and such)
### Balancing
- Reduce price of the lexica botania to use paper instead of books

## 0.0.3
- Make it not crash outside of the dev environment (oops)
  - Properly include a dependency that was accidentally excluded
- Make the water rod place flowing water outside the end
- Reduce the price of mana spreaders to a gold nugget instead of an ingot
- Allow the Drum of Gathering to properly shear sheep
- Add agricarnation check for special flowers (i.e., hopperhock)


## 0.0.2
- Fix a boat load of NPEs 
  - All flora should now be able to be placed at the very least (some do nothing still)
- Properly implement the creative tab 
- Allow the Rannuncarpus to work with more non-block items 
- Fix flower transmutation (too magical)
- Fix the flower pouch leaving ghost items behind

## 0.0.1
- Initial alpha release