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

package libraries.utils.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

/**
 * Base interface to describe different room data access object
 *
 * @author Mohammad Esteki
 * @param T Particular entity
 */
interface BaseDao<T> {

    /**
     * Insert data in table
     *
     * @param data Object for insert
     *
     * @return ID of inserted data
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: T): Long

    /**
     * Insert data list in table
     *
     * @param dataList Object list for insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dataList: List<T>)

    /**
     * Update data in table
     *
     * @param data Object for update
     **/
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(data: T)

    /**
     * Update data in table
     *
     * @param dataList Object list for update
     */

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(dataList: List<T>)

    /**
     * Delete particular data from table
     *
     * @param data Object requested to delete
     *
     * @return ID of deleted data
     */
    @Delete
    suspend fun delete(data: T): Int

    /**
     * Delete particular data list from table
     *
     * @param dataList Object list requested to delete
     */
    @Delete
    suspend fun delete(dataList: List<T>)
}
