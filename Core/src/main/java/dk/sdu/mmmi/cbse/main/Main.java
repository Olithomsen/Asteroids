package dk.sdu.mmmi.cbse.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ServiceLoader;
import java.util.List;

public class Main extends Application {

    public static void main(String[] args) {
        launch(String.valueOf(com.sun.tools.javac.Main.class));
    }

    @Override
    public void start(Stage window) throws Exception {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ModuleConfig.class);

        for (String beanName : ctx.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }

        Game game = ctx.getBean(Game.class);
        game.start(window);
        game.render();

    }
}
