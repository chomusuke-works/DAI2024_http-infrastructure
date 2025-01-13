import io.javalin.Javalin;
import io.javalin.plugin.bundled.CorsPluginConfig;
import zoo.*;

public class Main {
    private static final int PORT = 25565;

    public static void main(String[] args) {
        Javalin app = Javalin.create(config ->
            config.bundledPlugins.enableCors(cors ->
			    cors.addRule(CorsPluginConfig.CorsRule::anyHost)
		));

        AnimalController animals = new AnimalController(); // Instance of ZooApi

        // Routes
        app.after(ctx -> ctx.header("Access-Control-Allow-Origin", "*"));
        app.post("/api/animals", animals::create);
        app.get("/api/animals/{name}", animals::getOne);
        app.get("/api/animals", animals::getAll);
        app.put("/api/animals/{name}", animals::update);
        app.delete("/api/animals/{name}", animals::deleteOne);
        app.start(PORT);
    }
}