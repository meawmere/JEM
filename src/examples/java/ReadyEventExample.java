import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.meawmere.jem.JEM;
import net.meawmere.jem.infrastructure.InvalidSettingsException;
import net.meawmere.jem.infrastructure.annotations.*;
import net.meawmere.jem.infrastructure.annotations.events.Ready;

@EventListener
public class Test {
    
    @Constructor
    public Test() {}
    
    public static void main(String[] args) throws InvalidSettingsException, InstantiationException, IllegalAccessException {
        JEM jem = new JEM();
        jem.saveClass(Test.class);

        JDA jda = JDABuilder.createDefault("token")
                .addEventListeners(jem.handler())
                .enableIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS))
                .build();
        
        jem.assign();
    }

    @Ready
    public void test(@EventData ReadyEvent event) {
        System.out.println("Ready 2");
    }

    @Ready
    public @async void test2(@EventData ReadyEvent event) {
        System.out.println("Async Ready");
    }

    @Ready
    public static void onCommand(@EventData SlashCommandInteractionEvent event) { // error
        System.out.println("SlashCommandInteraction");
    }
}
