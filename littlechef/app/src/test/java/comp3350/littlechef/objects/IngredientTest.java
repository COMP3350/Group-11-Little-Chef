package comp3350.littlechef.objects;
import junit.framework.TestCase;
import org.junit.Test;
import java.util.ArrayList;


public class IngredientTest extends TestCase
{
    public IngredientTest(String arg0) {
        super(arg0);
    }

    @Test
    public void testSimple()
    {
        //test the file could run or not
        assertEquals(1, 1);
    }//end testSimple

    @Test
    public void testIngredientCreation()
    {
        ArrayList<Ingredient>inList = new ArrayList<>();
        Ingredient in1 = new Ingredient("a1", "aa1", 0.1d);
        assertNotNull(in1);
        inList.add(in1);

        Ingredient in2 = new Ingredient("b1", "bb1", 0.2d);
        inList.add(in2);
        assertEquals(2,inList.size());

        Ingredient in3 = new Ingredient("","",0.3d);
        inList.add(in3);
        assertEquals(3,inList.size());

        Ingredient in4 = new Ingredient(null,"mm",0.4d);
        inList.add(in4);
        assertEquals(4,inList.size());

        Ingredient in5 = new Ingredient("e1","ee1",5);
        inList.add(in5);
        assertEquals(5,inList.size());

        Ingredient in6 = new Ingredient("f1","mm", Double.MAX_VALUE);
        inList.add(in6);
        assertEquals(6,inList.size());

        Ingredient in7 = new Ingredient("f1","mm", Double.MAX_VALUE+1);
        inList.add(in7);
        assertEquals(7,inList.size());

    }//end testIngredientCreation

    @Test
    public void testIngredientGetName()
    {
        Ingredient in1 = new Ingredient("a1", "bb", 0.1d);
        assertNotNull(in1);

        assertEquals("a1", in1.getName());

        Ingredient in2= new Ingredient(null,"b2",0.2d);
        assertNull(in2.getName());

        Ingredient in3 = new Ingredient("","c3",0.3d);
        assertEquals("",in3.getName());
    }//end testIngredientGetName

    @Test
    public void testIngredientSetName()
    {
        //assertNotNull
        Ingredient i1 = new Ingredient("a", "b", 666);
        i1.setName("aa");
        assertEquals("aa", i1.getName());

        i1.setName("");
        assertEquals("",i1.getName());

        i1.setName(null);
        assertNull(i1.getName());
    }//end testIngredientSetName

    @Test
    public void testIngredientGetMeasurement()
    {
        Ingredient mea1 = new Ingredient("a1", "MM", 123);
        assertNotNull(mea1);

        assertEquals("mm", mea1.getMeasurement());
        assertNotSame("MM", mea1.getMeasurement());

        Ingredient mea2 = new Ingredient(null, "MM", 123);
        assertNotNull(mea2);

        Ingredient mea3 = new Ingredient(null, "null", 123);
        assertNotNull(mea3);

    }//end testIngredientGetMeasurement

    @Test
    public void testIngredientSetMeasurement()
    {
        //assertNotNull
        Ingredient mea2 = new Ingredient("a", "MM", 888);
        assertEquals("mm", mea2.getMeasurement());

        mea2.setMeasurement("PINCH");
        assertEquals("pinch", mea2.getMeasurement());

        mea2.setMeasurement("abcde");
        assertNotSame("abcde", mea2.getMeasurement());

        mea2.setMeasurement("");
        assertNotSame("",mea2.getMeasurement());

        //mea2.setMeasurement(null);
        //assertNull(mea2.getMeasurement());
    }//end testIngredientSetMeasurement

    @Test
    public void testIngredientGetAmount()
    {
        Ingredient amo1 = new Ingredient("a1", "b1", 0.1);
        assertNotNull(amo1);

        assertEquals(0.1, amo1.getAmount());

        amo1.setAmount(Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE,amo1.getAmount());

        amo1.setAmount(-0.1d);
        assertNotSame(-0.1d,amo1.getAmount());
        //System.out.println(amo1.getAmount());// no operation in setAmount
    }//end testIngredientGetAmount

    @Test
    public void testIngredientSetAmount()
    {
        //assertNotNull
        Ingredient amo2 = new Ingredient("a", "MM", 888.0);
        assertEquals(888.0, amo2.getAmount());

        amo2.setAmount(111);
        assertEquals(111.0, amo2.getAmount());

        amo2.setAmount(222);
        assertNotSame(333.0, amo2.getAmount());

        amo2.setAmount(-0.1d);
        assertNotSame(-0.1d,amo2.getAmount());

        amo2.setAmount(0.0);
        assertEquals(222.0,amo2.getAmount());
    }//end testIngredientSetMeasurement


    @Test
    public void testIngredientGetUnitType()
    {
        Ingredient unittype1 = new Ingredient("a", "MM", 0.1);
        assertEquals("SIZE", unittype1.getUnitType());

        unittype1.setMeasurement("PINCH");
        assertEquals("VOLUME", unittype1.getUnitType());

        unittype1.setMeasurement("MG");
        assertEquals("WEIGHT", unittype1.getUnitType());

        unittype1.setMeasurement("MG");
        assertNotSame("SIZE", unittype1.getUnitType());

        unittype1.setMeasurement("MM");
        assertNotSame("VOLUME", unittype1.getUnitType());

        unittype1.setMeasurement("PINCH");
        assertNotSame("SIZE", unittype1.getUnitType());

        unittype1.setMeasurement("");
        assertNotSame("",unittype1.getUnitType());

        unittype1.setMeasurement(" ");
        assertNotSame(" ",unittype1.getUnitType());
    }//end testIngredientGetUnitType

    @Test
    public void testIngredientGetDisplayMeasurement()
    {
        Ingredient disp1 = new Ingredient("a", "MM", 0.1);
        assertEquals("0.10 mm", disp1.getDisplayMeasurement());

        disp1.setAmount(0.22);
        assertEquals("0.22 mm", disp1.getDisplayMeasurement());

        disp1.setAmount(0.222);
        assertEquals("0.22 mm", disp1.getDisplayMeasurement());

        disp1.setAmount(0.229);
        assertEquals("0.23 mm", disp1.getDisplayMeasurement());

        disp1.setAmount(0.2222);
        assertEquals("0.22 mm", disp1.getDisplayMeasurement());

        disp1.setAmount(0.2229);
        assertEquals("0.22 mm", disp1.getDisplayMeasurement());

        disp1.setAmount(0.0);
        assertNotSame("0.00 mm",disp1.getDisplayMeasurement());

        disp1.setAmount(-0.1d);
        assertNotSame("-0.1d mm",disp1.getDisplayMeasurement());
        /*** ***/
    }//end testIngredientGetDisplayMeasurement

    @Test
    public void testsetUnitInformation()
    {
        Ingredient unitinfo1 = new Ingredient("a", "MM", 0.1);
        assertEquals("SIZE", unitinfo1.getUnitType());

        unitinfo1.setMeasurement("PINCH");
        assertEquals("VOLUME", unitinfo1.getUnitType());

        unitinfo1.setMeasurement("MG");
        assertEquals("WEIGHT", unitinfo1.getUnitType());

        unitinfo1.setMeasurement("MG");
        assertNotSame("SIZE", unitinfo1.getUnitType());

        unitinfo1.setMeasurement("MM");
        assertNotSame("VOLUME", unitinfo1.getUnitType());

        unitinfo1.setMeasurement("PINCH");
        assertNotSame("SIZE", unitinfo1.getUnitType());

        Ingredient unitinfo2 = new Ingredient(null, "ff", 0.1);
        assertNotSame("SIZE", unitinfo2.getUnitType());

        Ingredient unitinfo3 = new Ingredient(null, "", 0.1);
        assertNotSame("SIZE", unitinfo3.getUnitType());

        Ingredient unitinfo4 = new Ingredient("", "null", 0.1);
        assertNotSame("SIZE", unitinfo4.getUnitType());

        Ingredient unitinfo5 = new Ingredient(null, "", 0.0);
        assertNotSame("SIZE", unitinfo5.getUnitType());
    }//end testsetUnitInformation
}