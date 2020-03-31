package com.example.test.cassandra;

/*

import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.rest.core.annotation.RestResource;
import com.datastax.driver.core.utils.UUIDs;
import lombok.Data;

@Data
@RestResource(rel="categories", path="categories")
@Table("categories")
public class Category {
    // Cassandra databases are split across multiple partitions,
        // any row in a given table may be managed by one or more partitions
    // two types of keys: partitioning and clustering keys
        // hash operations are performed on each row’s partition key to determine which partition that row will be
        // clustering keys determine the order in which the rows are maintained within a partition
    // is highly optimized for read operations, it’s common and desirable for tables to be highly denormalized
        // and for data to be duplicated across multiple tables

    // Cassandra config: cqlsh
    // create keyspace reactcloud with replication={'class':'SimpleStrategy', 'replication_factor':1} and durable_writes=true;
    // application.properties

    @PrimaryKeyColumn(type=PrimaryKeyType.PARTITIONED)
    private UUID id = UUIDs.timeBased();

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @PrimaryKeyColumn(type=PrimaryKeyType.CLUSTERED, ordering=Ordering.DESCENDING)
    private Date createdAt = new Date();

    @Column("items")  // Cassandra duplication, but must be a collection of native types (string, int...)
    private List<ItemUDT> items;  // represented as a JSON array of values in CQLSH
}

*/