package LAB5;

public class MeowUtils {

    public static void makeAllMeow(Meowable... meowables) {   // метод заставляет всех мяукать
        for (Meowable m : meowables) { // проходим по каждому объекту
            m.meow(); // вызываем метод meow у текущего объекта
        }
    }
}
