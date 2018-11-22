package com.qa.intergration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.business.service.AccountService;
import com.qa.rest.AccountEndpoint;


@RunWith(MockitoJUnitRunner.class)
public class AccountEndpointTest {
	
	private static final String MOCK_VALUE = "test_value";
	private static final String MOCK_VALUE2 = "test_value_2";

	@InjectMocks
	private AccountEndpoint endpoint;

	@Mock
	private AccountService service;
	

	@Before
	public void setup() {
		endpoint.setService(service);
	}
	
    @Test
    public void testVerifyBasicInteractions() {
    
        service.addAccount("an account");
        String result =service.getAllAccounts();
        verify(service).addAccount("an account"); // verify behavior happened once
        verify(service, times(1)).getAllAccounts(); // again, verify behavior happened once
    }

	@Test
	public void testGetAllAccounts() {
		Mockito.when(service.getAllAccounts()).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, endpoint.getAllAccounts());
	}

	@Test
	public void testCreateAccount() {
		Mockito.when(service.addAccount(MOCK_VALUE2)).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, endpoint.addAccount(MOCK_VALUE2));
		Mockito.verify(service).addAccount(MOCK_VALUE2);
	}

	@Test
	public void testUpdateAccount() {
		Mockito.when(service.updateAccount(1L, MOCK_VALUE2)).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, endpoint.updateAccount(1L, MOCK_VALUE2));
		Mockito.verify(service).updateAccount(1L, MOCK_VALUE2);
	}

	@Test
	public void testDeleteAccount() {
		Mockito.when(service.deleteAccount(1L)).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, endpoint.deleteAccount(1L));
		Mockito.verify(service).deleteAccount(1L);
	}

}
