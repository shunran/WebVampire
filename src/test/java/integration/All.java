package integration;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ HappyPathIT.class, DieKillFailIT.class, InstructionIT.class})
public class All {

}
