package com.lemon.hgod;

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
            .importPackages("com.lemon.hgod");

        noClasses()
            .that()
            .resideInAnyPackage("com.lemon.hgod.service..")
            .or()
            .resideInAnyPackage("com.lemon.hgod.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.lemon.hgod.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
