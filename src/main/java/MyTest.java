public class MyTest {
    @BeforeSuite
    public static void beforeTest() {
        System.out.println("Запускаем тестирование\n");
    }

    @Test(description = "приоритет 8",priority = 8)
    public static void test1() {
        System.out.println("test1 is completed\n");
    }
    @Test(description = "приоритет 7", priority = 7)
    public static void test2() {
        System.out.println("test2 is completed\n");
    }
    @Test(description = "дефолтный приоритет 5")
    public static void test3() {
        System.out.println("test3 is completed\n");
    }
    @Test (description = "дефолтный приоритет 5")
    public static void test4() {
        System.out.println("test4 is completed\n");
    }
    @Test (description = "приоритет 1", priority = 1)
    public static void test5() {
        System.out.println("test5 is completed\n");
    }

    @AfterSuite
    public static void afterTest() {
        System.out.println("Тестирование окончено");
    }
}
