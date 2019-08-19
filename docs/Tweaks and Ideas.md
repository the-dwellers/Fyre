# Tweaks and Ideas

Minecraft is quite the misleading game.
Although the gameplay loop is quite simple, there are a ton of interactions by different mechanics.
Tweaks are edits to some mechanics, or entirely new mechanics.

This document is mostly a note of mechanic ideas an tweak brainstorming, feedback or comments are encouraged!

---

- [Tweaks and Ideas](#tweaks-and-ideas)
	- [Confirmed / Implemented Mechanics](#confirmed--implemented-mechanics)
	- [Mechanic ideas](#mechanic-ideas)
	- [🐲 Aims and Thoughts](#-aims-and-thoughts)

---

## Confirmed / Implemented Mechanics

- [x] Chests on boats
	- Players can shift-click boats to store items, breaking the boat drops the items
- [x] Longer Days
	- Daytime is twice as long, as it often feels too short
- [x] Saddled horses have AI disabled
	- Prevents horses from wandering away and getting lost (or sometimes killing themselves)
- [ ] Walking on grass has a chance to make paths
	- Shows common paths throughout places, quite effective when in small spaces
	- Also functions faster on horses, converting grass blocks into grasspaths

## Mechanic ideas

- [ ] Mob on-hit events
	- Phantoms Blind
	- Zombies Slow
	- Enderman apply weakness
- [ ] Some way to move spawners?
	- May be changed if underground is overhauled
	- Mob grinding is risk-free to get xp, not good.
- [ ] Extended potion crafting
	- Current brewing has a large amount of limits. Combine potions?
- [ ] Perk System
	- Allows adding mechanics for everyone, but perks make some stronger?
	- Uncrafting for example, perk would increase amount of resources obtained
- [ ] Uncrafting
	- Turn items into their components, might be a balance problem

## 🐲 Aims and Thoughts

Fyre's general aim is to provide a different way of playing a decade-old game.
It aims to be more of the adventuring-style MMO rather than the general sandbox Minecraft is.
We also want to avoid the "just another custom server", and so the main aim is to implement everything
as in-game mechanics, rather than commands, admin shops, teleport commands, etc.

So far the mechanics for this idea relies on using Traders to gate content.
Although this does add a progression system, this may cause some dislike as content
players are used to regularly using, is locked behind walls. Some effort to alleviate
this will be done by a total rebalance of armor and damage ratings, but this may yet again
cause some problems with power-creep, and long-term players completely dwarfing any new player.

Some research into using Minecraft's unused Attributes system for different equipment
mechanics is a must. Perhaps look into different scaling for player health? Everyone
is used to starting with 10 hearts, perhaps start with the 5 and add more depending on their armor.

Another challenge with rebalancing, is the enemy's in the game. Mobs are common
but generally weak, and while increasing the danger level of mobs does provide
more of a challenge, it is very hard to balance rewardable gaming and prevent annoyance.

Perhaps we can reduce mob spawn amounts? And still make them stronger.
This will result in rarer mobs, making avoidance easy for players, but
still provide a challenge for players who are looking for it.

As for Pvp, it will not be disabled and not regulated either, it might be interesting
to see how players band and group up. However, this should not be a pvp-focused server.

Due to human nature, there will be players who explicitly seek to grief other players
 some methods to counter this should be researched. Although the server will hav
  staff moderation, the aim is to reduce this as much as possible;- more relying on mechanics.

Currently we're not sure what to do for area protection;- we do not want player
 to come back onto the server only to find out their entire base and work was destroyed
  We also don't want entirely abandoned and protected areas, since the wilderness should be wild.

Something similar to towns perhaps? But this can cause a lack of creativity trying
to force settlements into chunks or regions, and then this will cause another
issue of claimed land, and how to deal with it. Things needed to keep in mind are:

- Chest and Item Protection
- Area claiming is messy, unused vertical space and not suitable for natural builds
	- Also applies to towns: they mix people together but plots are unnatural
- Faction power system moves the mechanics to favour pvp
- Don't want a player to lose everything while offline (or too far away online)
- Don't want a large amount abandoned man-made structures that are protected

From these points, perhaps have a "nature-reclamation" system? slowly spread grass and trees into
player areas that damage blocks (cobble-stone -> break, wood rot, stone bricks crack, etc)
Pretty good mechanics wise, solves the issue with abandonment, but what about actual protection?
Protection can be based on the state of the base, but how on earth can we keep it performant?

Perhaps to solve the town issue, have player-created towns containing npcs?

More view points on this area is needed. Would require a system that is "in-game"
e.g use mechanics rather than chat. This is annoying, since a protection system is much-needed
by any server for online use, and dictates a large amount of gameplay. Any ideas at all are welcome, even
ones on why already-discarded ideas should be used!

For the record, these are the available protection systems and the main sticking points:

- Towns
	- Too large and focused on chat-gameplay, overly complex solution for the protection issue.
	- Claiming is chunk or region-based
	- Town can be abandoned or changed while offline
	- More of a hierarchical ownership, rather than group
- Factions
	- Too pvp-oriented, with power based on deaths and player kills
	- Claiming is chunk or region-based
	- Faction can get taken over while offline
	- More of a hierarchical ownership, rather than group
- Lockable chests (and doors)
	- Protects chests only, buildings can still get griefed
	- claims don't expire, resulting in inaccessible chests
	- Can be annoying to add everyone to chest claims
- Grief Prevention
	- Claiming is chunk or region-based
	- Claims don't expire, resulting in abandoned inaccessible places
