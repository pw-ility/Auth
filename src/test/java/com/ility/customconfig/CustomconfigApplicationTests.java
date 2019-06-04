package com.ility.customconfig;

import com.ility.customconfig.beans.AppProperty;
import com.ility.customconfig.repo.AppPropertyRepository;
import com.ility.customconfig.services.AppPropertyService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ility.customconfig.beans.AppProperty;
import com.ility.customconfig.repo.AppPropertyRepository;
import com.ility.customconfig.services.AppPropertyService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomconfigApplicationTests {

	@Autowired
	private AppPropertyService appPropertyService;
	@Autowired
	private AppPropertyRepository appPropertyRepository;
	@Before
	public void init(){
		System.out.println("start test----------------");
	}
	@After
	public void after(){
		System.out.println("end  test----------------");
	}
	@Test
	public void contextLoads() {
	}



	@Test
	public void testGetPropertyById(){
		AppProperty appProperty=appPropertyRepository.findOneById(6);
		Assert.assertNotEquals(appProperty,null);

		AppProperty appProperty1=appPropertyRepository.findOneById(1);
		Assert.assertEquals(appProperty1,null);
	}
	@Test
	public void testDelete(){

		try{

			AppProperty appProperty=appPropertyRepository.findByCriteria("ility","test","master","framework",false);
			appPropertyService.delete("ility","test","master","framework");
			AppProperty appPropertyAfterDelete=appPropertyRepository.findOneById(appProperty.getId());
			Assert.assertEquals(true,appPropertyAfterDelete.getDelete());
		}catch (Exception e){
			System.out.println(e.getMessage());
		}

	}

	@Test(expected = Exception.class)
	public void testUpdate() throws Exception{
		//test if property id=0 or null
		AppProperty appProperty=new AppProperty();
		appProperty.setApplication("ility");
		appProperty.setProfile("test");
		appProperty.setKey("framework");
		appProperty.setLabel("master");
		appProperty.setValue("hibernate");
		appPropertyService.updateById(appProperty);
	}

	@Test
    public void testEncrypt(){

		AppProperty appProperty=new AppProperty();
		appProperty.setValue("encrypt.123456"); //password is 123456 , "encrypt." for tag this value should be encrypted.
		appProperty.setKey("password");
		appProperty.setProfile("test");
		appProperty.setApplication("ility");
		appProperty.setLabel("master");
		appProperty.setDelete(false);

		AppProperty appPropertyEncrypt=appPropertyService.save(appProperty);//encrypt

		//decrypt
		AppProperty appPropertyDecrypt=appPropertyRepository.findByCriteria(appPropertyEncrypt.getApplication(),
				appPropertyEncrypt.getProfile(),appProperty.getLabel(),appProperty.getKey(),false);

		Assert.assertEquals("123456",appPropertyDecrypt.getValue());
	}
}

