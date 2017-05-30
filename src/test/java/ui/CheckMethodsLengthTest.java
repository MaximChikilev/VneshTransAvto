package ui;

import junit.framework.Assert;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.testng.IObjectFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@PrepareForTest(CheckUtils.class)
public class CheckMethodsLengthTest {

    @DataProvider
    public Object[][] dataForLength() throws Exception {
        return new Object[][]{{"123456", 6, true}, {"Тридцать", 7, false}};
    }

    @ObjectFactory
    public IObjectFactory setObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @Test(dataProvider = "dataForLength")
    public void testGetValueAttribute(final String inputLine, final int length, final Boolean returnResult) throws Exception {
        mockStatic(CheckUtils.class);
        when(CheckUtils.checkLength(inputLine, length)).thenCallRealMethod();
        Assert.assertEquals(CheckUtils.checkLength(inputLine, length), returnResult);
    }
}
