package org.bahmni.gauge.common.clinical.displaycontrol;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ObsDisplayControlTest {

	@Mock
	private WebElement displayControlElement;

	@Mock
	private WebElement headerElement;

	@Mock
	private WebElement errorMessageElement;

	@Before
	public void setUp() {
		initMocks(this);
	}

	/*@Test
	public void ensureThatTitleIsReturnedProperly() {
		ObsDisplayControl obsDisplayControl = new ObsDisplayControl(displayControlElement);
		when(displayControlElement.findElement(By.tagName("h2"))).thenReturn(headerElement);
		when(headerElement.getText()).thenReturn("Treatment Initiation");

		assertEquals("Treatment Initiation", obsDisplayControl.getTitle());
	}

	@Test
	public void ensureThatPlaceHolderTextIsReturnedProperly() {
		ObsDisplayControl obsDisplayControl = new ObsDisplayControl(displayControlElement);
		when(displayControlElement.findElement(By.xpath("//p[@translate='DISPLAY_CONTROL_ERROR_MESSAGE_KEY']")))
				.thenReturn(errorMessageElement);
		when(errorMessageElement.getText()).thenReturn("No Treatment Initiation Found");
		assertEquals("No Treatment Initiation Found", obsDisplayControl.getNoRecordsFoundText());
	}

	@Test
	public void ensureThatPlaceHolderTextIsNullWhenNotFound() {
		ObsDisplayControl obsDisplayControl = new ObsDisplayControl(displayControlElement);
		when(displayControlElement.findElement(By.xpath("//p[@translate='DISPLAY_CONTROL_ERROR_MESSAGE_KEY']")))
				.thenReturn(null);
		assertNull(obsDisplayControl.getNoRecordsFoundText());
	}*/

}
