package koschei.config;

import koschei.models.Egg6;
import koschei.models.Island2;
import koschei.models.Needle7;
import koschei.models.Wood3;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "koschei")
public class AppConfig {

    @Bean
    public static Island2 getIsland(Wood3 wood) { //Ocean1 связан с Island2 через поле и аннотацию, которая подтянет бин Island2 через метод getIsland() в классе AppConfig
        return new Island2(wood);
    }
    @Bean
    public static Egg6 getEgg6(Needle7 needle) {
        return new Egg6(needle);
    }

}
