import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
         Class test = MyTest.class;
        try {
            start(test);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

   public static void start(Class testClass) throws InvocationTargetException, IllegalAccessException {
       int i=0; int j=0;
       ArrayList<Method> tests = new ArrayList<>();
       Method[] methods = testClass.getDeclaredMethods();
       for (Method m : methods) {
           if (m.isAnnotationPresent(BeforeSuite.class)) {
              i++;
               if (i>1) {
                   throw new RuntimeException("аннотации @BeforeSuite должны присутствовать" +
                           " в единственном экземпляре");}
               else  {
                   System.out.println(m.getAnnotation(BeforeSuite.class).description());
                   m.invoke(null);
               }
           }

           if (m.isAnnotationPresent(AfterSuite.class)) {
               j++;
               if (j > 1) {
                   throw new RuntimeException("аннотации @AfterSuite должны присутствовать" +
                           " в единственном экземпляре");
               }
           }

           if (m.isAnnotationPresent(Test.class)) {
           tests.add(m);
          }
       }
       tests.sort(Comparator.comparingInt(x -> x.getAnnotation(Test.class).priority()));
       for (Method testsTest : tests) {
           System.out.println(testsTest.getAnnotation(Test.class).description());
           testsTest.invoke(null);
       }
       for (Method n : methods) {
           if (n.isAnnotationPresent(AfterSuite.class)) {
               n.invoke(null);
               System.out.println(n.getAnnotation(AfterSuite.class).description());
           }
       }
   }
}
