package com.example.lesson6.service.option;

import com.example.lesson6.model.Option;
import com.example.lesson6.service.option.argument.UpdateOptionArgument;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static util.ResourceUtils.parseJson;

/**
 * Created on 17.11.2023.
 * <p>
 *
 * @author Denis Matytsin
 */
@EnablePostgresIntegrationTest
@ExtendWith(SoftAssertionsExtension.class)
public class OptionServiceImplIT {

    @Autowired
    private OptionService service;

    private final UUID optionId = UUID.fromString("00000001-0000-0000-0000-000000000001");

    @Test
    @DataSet("/datasets/service/option/update.json")
    @ExpectedDataSet("/datasets/service/option/update__expected.json")
    void execute(SoftAssertions softly) {
        // Arrange
        UpdateOptionArgument argument = UpdateOptionArgument.builder()
                                                            .name("name2")
                                                            .additionalPrice(2)
                                                            .build();
        Option expected = parseJson("/json/service/option/update__expected.json", Option.class);

        // Act
        Option actual = service.update(optionId, argument);

        // Assert
        softly.assertThat(actual)
              .usingRecursiveComparison()
              .withStrictTypeChecking()
              .isEqualTo(expected);
    }
}
