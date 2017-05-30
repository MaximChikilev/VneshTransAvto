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
public class CheckMethodsMaxLengthTest {

    @DataProvider
    public Object[][] dataForMaxLength() throws Exception {
        return new Object[][]{{"three", 8, true}, {"thousand", 5, false}};
    }

    @ObjectFactory
    public IObjectFactory setObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @Test(dataProvider = "dataForMaxLength")
    public void testGetValueAttribute(final String inputLine, final int maxLength, final Boolean returnResult) throws Exception {
        mockStatic(CheckUtils.class);
        when(CheckUtils.checkMaxLength(inputLine, maxLength)).thenCallRealMethod();
        Assert.assertEquals(returnResult, CheckUtils.checkMaxLength(inputLine, maxLength));
    }

}
