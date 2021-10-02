package com.staff.house;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.staff.house");

        noClasses()
            .that()
            .resideInAnyPackage("com.staff.house.service..")
            .or()
            .resideInAnyPackage("com.staff.house.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.staff.house.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
