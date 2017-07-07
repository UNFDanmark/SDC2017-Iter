package software.unf.dk.itergame;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by deltager on 07-07-17.
 */

public class GameStory {
    private ArrayList<String> story;

    public void setup() {
        story = new ArrayList<>();
        //Beginning og story
        //Prologue
        story.add("Once upon a time, it was a common practice for aspiring adventures to explore the mystical caverns of the Badlands, in search for a sense of purpose.");
        story.add("Many fabled adventures succeeded this journey of self discovery, and passed down in history as legends.");
        //Beginning of game and tutorial
        story.add("Protagonist: “it’s surprisingly bright in here”");
        //The protagonist notices a character(The watcher)

        story.add("The Watcher: “Greetings young one. I am the watcher of these sacred caverns \nI am the spirit tasked with preparing adventures for their journey");
        story.add("On your journey you can explore, but be ware that as a hero you must interact with your surroundings \nas well as other adventures, and also there will be a point where participating in battle fighting is inevitable");
        story.add("Use the pad in the bottom left corner to move around \nUse the buttons in the bottom right corner to interact and attack \nTry moving towards the dummy and attack it");
        story.add("The Watcher: “Well done, you may now enter the armory and choose your weapon for your journey”");
        //Tutorial ends and the character have to pick their class

        story.add("You stand in the room where all the stories you listened to as a child began \nAn armory that predates history itself");
        story.add("Time has however taken it’s toll on this once magnificent room, \nand among what was once a vast numbers of riches and weapons, only three items remain");
        story.add("A sword with its shield, a bow and its arrows and a two-handed axe");
        story.add("You pick the axe");
        //next scene
        //Spilleren går ind ad døren, som åbner sig efter han har valgt et våben. Han går derefterefter hen i ad en gang
        // Pludselig ser spilleren at der er en anden spiller der er i gang med kombat. Der skal være et choice her hvor spilleren kan vælge mellem at hjælpe, eller ikke at hjælpe.
        //Hvis han/hun vælger at hjælpe starter de ud på en bedre fod.
        story.add("Main characters thoughts: He looks like he's having troubles with those monsters.");
        story.add("Main characters thoughts: I should probably help him.");
        // Spilleren går hen imod personen og monstrene og begynder at gå i kombat imod dem.
        story.add("Thanks stranger, so you're also trying to reach the rank of legends? \nWanna tag along I could use partner ");
        story.add("I guess you could say that, and yeah sure");
        story.add("Well, I am Jarick, let's go be legends");
        //Spilleren og hans nye partner drager videre gennem den næste passage...
        //Der sker ikke noget i et stykke tid og der kan eventurelt være en time skip
        story.add("Jarick: We have been going around for hours... \n I need to do something soon");
        story.add("Yeah, I suppose I expected more from this cavern");
        //De følges ad lidt længere...
        story.add("MC: So tell me Jarick \n Who are you?");
        story.add("Jarick: Oh me \n Well I am just a hunter looking for bigger prey");
        story.add("MC: What kind of prey are we talking?");
        story.add("Jarick: Haven't you heard the stories? \nWithin these caverns lies some of the most monstrous beasts ever written about");
    }

    public ArrayList<String> getStory(){
        return story;
    }
}
