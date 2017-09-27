package ua.nure.dominov.SummaryTask4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ua.nure.dominov.SummaryTask4.db.bo.bo.impl.TourBoImplTests;
import ua.nure.dominov.SummaryTask4.db.bo.bo.impl.UserBoImplTests;
import ua.nure.dominov.SummaryTask4.db.bo.bo.impl.VerificationCodeBoImplTests;
import ua.nure.dominov.SummaryTask4.db.connectionpool.ConnectionPoolTests;
import ua.nure.dominov.SummaryTask4.util.HashCodeTests;
import ua.nure.dominov.SummaryTask4.util.MoneyEmountCounterTests;
import ua.nure.dominov.SummaryTask4.util.SecureTests;

@RunWith(Suite.class)
@SuiteClasses({ SecureTests.class, HashCodeTests.class,
	MoneyEmountCounterTests.class, ConnectionPoolTests.class, 
	TourBoImplTests.class, UserBoImplTests.class, 
	VerificationCodeBoImplTests.class})
public class AllTests { }