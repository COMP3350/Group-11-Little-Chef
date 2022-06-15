package comp3350.littlechef.objects;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.ArrayList;


public class IngredientTest extends TestCase {
    public IngredientTest(String arg0) {
        super(arg0);
    }

    @Test
    public void testSimple() {
        //test the file could run or not
        assertEquals(1, 1);

    }

    @Test
    public void testIngredientCreation() {
        Ingredient in1 = new Ingredient("a1");
        assertNotNull(in1);

        Ingredient in2 = new Ingredient("b1");

        ArrayList<Ingredient>inList = new ArrayList<>();
        inList.add(in1);
        inList.add(in2);

        assertEquals(inList.size(), 2);
    }

    @Test
    public void testIngredientCreation2() {
        Ingredient in1 = new Ingredient("a1", "bb", 123);
        assertNotNull(in1);

        Ingredient in2 = new Ingredient("bbb1","ccc", 666);

        ArrayList<Ingredient>inList = new ArrayList<>();
        inList.add(in1);
        inList.add(in2);

        assertEquals(inList.size(), 2);
    }

    @Test
    public void testIngredientGetName() {
        Ingredient in1 = new Ingredient("a1", "bb", 123);
        assertNotNull(in1);

        assertEquals("a1", in1.getName());
    }

    @Test
    public void testIngredientSetName(){
        //assertNotNull
        Ingredient i1 = new Ingredient("a");
        i1.setName("aa");
        assertEquals("aa", i1.getName());
    }

    @Test
    public void testIngredientGetMeasurement() {
        Ingredient mea1 = new Ingredient("a1", "bb", 123);
        assertNotNull(mea1);

        assertEquals("bb", mea1.getMeasurement());
    }

    @Test
    public void testIngredientSetMeasurement(){
        //assertNotNull
        Ingredient mea2 = new Ingredient("a");
        mea2.setMeasurement("aa");
        assertEquals("aa", mea2.getMeasurement());
    }

//    @Test
//    public void testIngredientGetAmount() {
//        Ingredient amo1 = new Ingredient("a1", "bb", 123);
//        assertNotNull(amo1);
//
//        assertEquals("a1", amo1.getAmount());
//    }
//
//    @Test
//    public void testIngredientSetAmount(){
//        //assertNotNull
//        Ingredient amo2 = new Ingredient("a");
//        amo2.setAmount();
//        assertEquals("1", amo2.getAmount());
//    }

}