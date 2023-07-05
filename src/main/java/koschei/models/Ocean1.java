package koschei.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Ocean1 {

    @Autowired
    private Island2 island;   //Ocean1 связан с Island2 через поле и аннотацию, которая подтянет бин Island2 через метод getIsland() в классе AppConfig

    @Override
    public String toString() {
        return "на океане остров " + island.toString();
    }
}
