/*
 * Copyright 2021 MohammadEsteki.ir
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("UnstableApiUsage")

import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.LibraryProductFlavor
import org.gradle.api.NamedDomainObjectContainer

interface BuildProductFlavor {
    val name: String

    fun libraryCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>
    ): LibraryProductFlavor

    fun appCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>
    ): ApplicationProductFlavor
}

object ProductFlavorDevelop : BuildProductFlavor {
    override val name = "dev"

    override fun appCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>
    ): ApplicationProductFlavor {
        return namedDomainObjectContainer.create(name) {
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            dimension = BuildProductDimensions.ENVIRONMENT
        }
    }

    override fun libraryCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>
    ): LibraryProductFlavor {
        return namedDomainObjectContainer.create(name) {
//      versionNameSuffix = "-dev"
            dimension = BuildProductDimensions.ENVIRONMENT
        }
    }
}

object ProductFlavorQA : BuildProductFlavor {
    override val name = "qa"

    override fun appCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>
    ): ApplicationProductFlavor {
        return namedDomainObjectContainer.create(name) {
            applicationIdSuffix = ".qa"
            versionNameSuffix = "-qa"
            dimension = BuildProductDimensions.ENVIRONMENT
        }
    }

    override fun libraryCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>
    ): LibraryProductFlavor {
        return namedDomainObjectContainer.create(name) {
//      versionNameSuffix = "-qa"
            dimension = BuildProductDimensions.ENVIRONMENT
        }
    }
}

object ProductFlavorProduction : BuildProductFlavor {
    override val name = "prod"

    override fun appCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>
    ): ApplicationProductFlavor {
        return namedDomainObjectContainer.create(name) {
            dimension = BuildProductDimensions.ENVIRONMENT
        }
    }

    override fun libraryCreate(
        namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>
    ): LibraryProductFlavor {
        return namedDomainObjectContainer.create(name) {
            dimension = BuildProductDimensions.ENVIRONMENT
        }
    }
}
