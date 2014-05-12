Terminologi
===========

Staket = separerar tomter; namnet på den adligaste av sporter även kallat
fäktning.

Värja = svärd som används inom sporten staket.

Användare = någon som kontrollerar en värjhållare.

Värjhållare = någon som håller en värja (se "Värja")


Programbeskrivning
==================

    "Beskriv detaljerat vad programmet gör."

Det handlar om staket, eller fäktning som det heter på svenska. Ett
fightingspel där två spelare möter varandra.

#detaljerat

Programmet kompileras och körs ifrån command-line. Direkt kastas användarna in i
en match till "döden" (det har ännu inte bevisas ifall virtuella karaktärer
faktiskt dör). Användarna har valet att kunna blockera sin motståndares värja
eller att vifta i deras riktning. Karaktärerna kan även förflytta sig vänster
och/eller höger om det så ska krävas. I krig och kärlek finns inga regler. Ifall
någon av kämparna skulle dö kommer spelplanen automagiskt att återställas och en
poäng ges till den vinnande värjhållaren. Användarna spelar tills de inte längre
orkar bevittna blodutgjutelse.

Värjhållare A har tidigare under kvällen stött på värjhållare Bs syster. Dat
plot twist. I och med detta utmanar B A på en (pub)runda staket™. För att visa
att familjen är det viktigaste i hens värld.
	
Användarbeskrivning
===================

    "Vem kommer att använda ert program? Vilka antaganden gör ni om användarna?
    Är de vana datoranvändare, är de specialister, nybörjare, små barn, etc."

Användare: goda vänner med meningsskiljaktigheter som vill slåss till döden,
fast på låtsas.

Antaganden: har en kaffemaskin i datorn, alternativt fungerar Java; kan starta
och kompilera program ifrån command-line; har sett och eller använt ett
tangentbord förut; är etniskt svensk, australiensisk inföding eller något
däremellan; vet vad sporten staket innebär; kan läsa och skriva; kommer gissa
att WASD och piltangenterna är bra knappar att testa.

De är: människor (rimligtvis); vana datoranvändare; gamershs. 

Användarscenarier
=================

    "Ge minst två exempel på scenarier där en av era tänkta användare använder
    programmet. Beskriv i detalj vad de ser, vilken typ av input de måste ge,
    hur de ger sin input och hur programmets output ser ut."

Scenario 1:
-----------

Vad de ser:
Se figur 1.

Input:

Användaren kompilerar och startar programmet via command-line. Väl inne i spelet
har en match till döden redan börjat och användaren trycker frenetiskt på
piltangenterna eller WASD för att vidarebefordra sin ilska till sin motståndares
avatar.

Output:

Karaktären på skärmen rör sig hatiskt mot sin virtuella motståndare samt svingar
sin värja med obeskrivbar finess.

Scenario 2:
-----------

Vad de ser:
Se figur 2.

Input:

Användaren kompilerar och startar programmet via command-line. Eftersom denna
användare inte är insatt i gentlemanna sporter som staketning behövs
instruktioner. I verkligheten kommer de båda utmanarna överens om att gå igenom
reglerna för sporten. Med finess klickar användaren på 'q', som står för qultur,
för att utbilda sig inom sporten.  

Output:

En nedvärderande låt börjar höras genom högtalarna för att påvisa användarens
dräggighet. Samt visas en informativ ruta som förklarar tangenter och regler som
sedan kan avisas med ett godtyckligt knapptryck för att sedan återgå till
ärofylld kamp.

Figur 1
-------

    +--------------------------------------------+
    |                                            |
    |                                            |
    |                                            |
    |                                            |
    |                                            |
    |    0  |                            |  Ø    |
    |    +--+                            +--+    |
    |    |                                  |    |
    |    /\                                /\    |
    | ========================================== |
    +--------------------------------------------+


Figur 2
-------

    +--------------------------------------------+
    |                                            |
    |   +------------------------------------+   |
    |   | DØda motståndaren.  Frågor på det? |   |
    |   +------------------------------------+   |
    |                                            |
    |    0  |                            |  Ø    |
    |    +--+                            +--+    |
    |    |                                  |    |
    |    /\                                /\    |
    | ========================================== |
    +--------------------------------------------+

Testplan
========

    "Beskriv hur ni tänker testa programmet. I den här uppgiften ska ni lägga
    extra vikt vid användartestning. Beskriv vilka uppgifter som testanvändaren
    ska utföra. De två användarscenarierna ska ingå i användartestningen."

Vi planerar att ge två personer en anledning att slåss och sedan låter dem göra
det. Fast på låtsas, såklart. Försökskaninerna kommer slåss minst 2147483648
gånger och varje gång testa att avvärja, attackera och förflytta sig runt på
planen; de kommer även under denna tid glömma bort vad de håller på med och
därför ta fram instruktionerna.

Programdesign
=============

    "Beskriv de grundläggande klasserna som ni avser att implementera och ge en
    beskrivning av de viktigaste metoderna i varje klass."

Staket är sammanslagningen av alla bakomliggande klasser för att starta spelet.

Staket {
	// Main loopen.
	void run(DisplayMode dm)

	// Hämtar in bilder.
	void loadPics()
}

Character hanterar information om en värjhållare, bilder, position och poäng
etc.

Character {
	// Hämtar karaktärens x-position.
	int X()
	// Hämtar karaktärens statiska y-position (karaktärer kan inte hoppa).
	int Y()
	// Hämtar aktuell animation ifrån bakomliggande sprite.
	Sprite getSprite()
}

Sprite är en samling av bilder för att kunna skapa animationer.

Sprite {
	// Hämtar in en animation ifrån en bildfil.
	void setSprite(String path)	
	// Hämtar en speciell bild ifrån en sprite.
	Image getSprite(int num)
}

KeyInputHandler hanterar knapptryckningar.

KeyInputHandler {
	// Hookar ifall en knapp blir nedtryckt.
	public void keyPressed(KeyEvent e)
}

Screen gör ett fullskärmsfönster.

Screen {
	void setFullScreen(DisplayMode dm, JFrame window)	
}

Tekniska frågor
===============

    "En lista av tekniska frågor som ni måste hantera när ni bygger ert system.
    Var så detaljerad som möjligt. Ett viktigt steg mot en god design är att få
    ner så många frågor som möjligt på papper på ett organiserat sätt med så
    många förslag till lösningar som möjligt."

Hur ska vi hantera multipla knapptryckningar samtidigt?
	En ArrayList av knapptryckningar som töms var 50ms?

Hur ska poängräkningssystem se ut?
	Pongaktigt poängsystem?

Hur ska vi visa hjälptext för nya spelare?
	Konstant textremsa någonstans på skärmen?

Hur ska hitboxsystemet fungera?	
	Beräkna avståndet mellan värja och karaktär. Är det <= 0 så är det
	exempelvis en träff. Hitboxsystemet kommer kräva trådar för att registrera
	attacker mot en karaktär som går in i en värja.

Hur ska attacker fungera?
	Se hitboxsystemet.

Hur ska avvärjning av attacker fungera?
	Ifall två värjor under någon gång under animationens gång har avstånd <= 0
	till varandra sker en avvärjning och de båda karaktärerna förblir oskadda.
	Vid en avvärjning körs karaktärernas animationer baklänges motsvarande hur
	länge de kört. Dvs. ju senare en avvärjning sker desto mer fördelaktigt är
	det

Hur ska förflyttning fungera?
	Karaktärernas hitbox och animationer förflyttas i x-led. 

Arbetsplan
==========

	"Beskriv hur arbetet kommer att delas upp mellan personerna i projektet. Gör
	en tidsplan som visar när olika delmoment i projektet ska vara klara."

Bly Spritekonstnär: Richard Uggelberg
Bly Utvecklare (fram till quarnevalen): Henry Eklind
Bly Utvecklare (under quarnevalen): Richard Uggelberg


Till Övning 12 (2/5)

	"Inlämning av projektplan samt muntlig lägesrapport."

Skriva klart detta dokument.
Fixa en fungerande utvecklingsmiljö.
Lyckas förflytta en bild på skärmen.

Till Övning 13 (9/5)

	"Muntlig lägesrapport."

Richard:
	Fixa sprites
	Eventuellt logotyp (splash screen!)
	Fixa git
	Fixa musik

Henry:
	Skriva Character-klassen
	Skriva Sprite-klassen
	Implementera förflyttning
	Implementera poängsystemet
	Implementera attacksystemet
	Trådar i Java för hitboxsystemet

Till Övning 14 (16/5)

	"Inlämning av slutrapport, färdig kod samt muntlig slutrapport."

Richard:
	Fixa animationer
	Skriva slutrapport
	Göra det som är kvar
	Fixa ljudeffekter
	Implementera 8dimensionell blodspillsmotor

Henry:
	Implementera avvärjning
	Skriva slutrapport

