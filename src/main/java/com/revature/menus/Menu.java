package com.revature.menus;

import java.util.List;

import com.revature.drivers.Driver;
import com.revature.pojos.Car;
import com.revature.pojos.Offer;
import com.revature.pojos.Payment;
import com.revature.pojos.User;
import com.revature.services.CarManagementService;
import com.revature.services.OfferManagementService;
import com.revature.services.PaymentManagementService;
import com.revature.services.UserLoginService;

public class Menu {
	
	protected static User current = null;
	protected static UserLoginService uls = new UserLoginService(Driver.myLot);
	protected static CarManagementService cms = new CarManagementService(Driver.myLot);
	protected static OfferManagementService oms = new OfferManagementService(Driver.myLot);
	protected static PaymentManagementService pms = new PaymentManagementService(Driver.myLot);
	
	protected static List<Car> carDB = Driver.myLot.getCars();
	protected static List<Offer> offerDB = Driver.myLot.getOffers();
	protected static List<Payment> paymentDB = Driver.myLot.getPayments();
}
