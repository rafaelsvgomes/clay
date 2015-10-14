import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.clay.entidade.Pessoa;

public class PessoaTeste {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void test() {
        Pessoa p = new Pessoa();
        p.setNomePessoa("Teste");
        p.setDescEmail("rafael123@gmail.com");

        Set<ConstraintViolation<Pessoa>> constraintViolations = validator.validate(p);
        Assert.assertEquals(1, constraintViolations.size());
    }

}
