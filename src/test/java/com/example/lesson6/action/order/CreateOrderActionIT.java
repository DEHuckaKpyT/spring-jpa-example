package com.example.lesson6.action.order;

import com.example.lesson6.action.order.argument.CreateOrderActionArgument;
import com.example.lesson6.model.Order;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import static util.ResourceUtils.parseJson;

/**
 * Created on 17.11.2023.
 * <p>
 *
 * @author Denis Matytsin
 */
@EnablePostgresIntegrationTest
@ExtendWith(SoftAssertionsExtension.class)
public class CreateOrderActionIT {

    @Autowired
    private CreateOrderAction action;

    @Test
    @DataSet("/datasets/action/order/create.json")
    @ExpectedDataSet(value = "/datasets/action/order/create__expected.json", orderBy = "option_id")
    void execute(SoftAssertions softly) {
        // Arrange
        CreateOrderActionArgument argument = parseJson("/json/action/order/create-argument.json", CreateOrderActionArgument.class);
        Order expected = parseJson("/json/action/order/create__expected.json", Order.class);

        // Act
        Order actual = action.execute(argument);

        // Assert
        softly.assertThat(actual)
              .usingRecursiveComparison()
              .withStrictTypeChecking()
              .ignoringFields("id", "createDate")
              .isEqualTo(expected);
    }
}
