"Programdesign. Beskriv de grundläggande klasserna som ni avser att implementera
och ge en beskrivning av de viktigaste metoderna i varje klass."

Staket
======

Staket är själva spelklassen där mycket gemensam data finns samlad.
Spelkaraktärerna, deras startkoordinater till exempel.

redraw
------
	Forcera en omritning av skärmen.

paintComponent
--------------
	Override funktion som ritar objekt till skärmen.

positionChallengers
-------------------
	Förflyttar karaktärerna till deras start positioner.

loadPics
--------
	Ladda in karaktärernas animationer och bakgrunden.

run
---
	Huvudmetoden som sedan startar skärmen.

Character
=========

Character hanterar allt om enstaka karaktärer och deras placering. 

isBlocking
----------
	Håller den här karaktären för nuvarande på att avvärja attacker?

attackAnim, blockAnim
---------------------
	Spela upp attack / blockerings animationen.

moveRight, moveLeft
-------------------
	Förflytta karaktären.
		
stopAnimations
--------------
	Stoppa alla animationer.

KeyInputHandler
===============

KeyInputHandler hanterar alla knapptryckningar och logiken som inträffar vid
sagd knapptryckning. Attack, blockering och förflyttningar lyssnas efter
här.

charActions
-----------
	Tar reda på vilken spelare som tryckte på knappen för att
	sedan utföra kommandot. En convenience function som generaliserar
	koden för de båda spelarna.

keyPressed
----------
	Override function son krävs för att hooka på knapptryckningar.

moveBack, moveForward
---------------------
	Convenience functions för att flytta spelarna bakåt respektive
	framåt (vilket är motsatt för varandra eftersom de står ansikte mot
	ansikte).

stopTimers
----------
	Stanna båda fäktarnas animationer.

Screen
======

Screen möjligör fullskärmsrendrering.

setFullScreen
-------------
	Självförklarande.


