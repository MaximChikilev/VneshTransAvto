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

@PrepareForTest({CheckUtils.class})
public class CheckMethodsIsInteger {

    @DataProvider
    public Object[][] dataForIsInteger() throws Exception {
        return new Object[][]{{"8", true}, {"5", true}, {"6.0", false}};
    }

    @ObjectFactory
    public IObjectFactory setObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @Test(dataProvider = "dataForIsInteger")
    public void testGetValueAttribute(final String inputLine, final Boolean returnValue) throws Exception {
        mockStatic(CheckUtils.class);
        when(CheckUtils.isInteger(inputLine)).thenCallRealMethod();
        Assert.assertEquals(CheckUtils.isInteger(inputLine), returnValue);
    }

}
