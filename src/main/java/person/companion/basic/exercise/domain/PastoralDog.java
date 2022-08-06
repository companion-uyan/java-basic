package person.companion.basic.exercise.domain;

import person.companion.basic.domain.Dog;

/**
 * 功能描述：
 *
 * @author companion
 * @date 2020/5/29 18:39
 */
public class PastoralDog extends Dog {
    public PastoralDog() {
        System.out.println("PastoralDog class constructor");
        sayDog();
    }

    private void sayDog(){
        System.out.println("not override method--PastoralDog class");
    }
}
