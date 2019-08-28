package com.techprimers.springbootneo4jexample1.repository;

import com.techprimers.springbootneo4jexample1.model.Movie;
import com.techprimers.springbootneo4jexample1.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface UserRepository extends Neo4jRepository<User, Long> {

    @Query("MATCH (u:User) RETURN u")
    Collection<User> getAllUsers();


    @Query("MATCH (u:User)<-[r:RATED]-(m:Movie) RETURN u,r,m")
    Collection<User> getAllMaps();

    @Query("MATCH (u:User) WHERE u.name={title} RETURN u")
    User findByName(@Param("title") String title);

    @Query("match ({user}),({movie}) where {user}.name={UserName} and {movie}.name={MovieName} merge ({user})-[r:RATED]->({movie}) return r")
    Collection<User> createRelationships(User user,Movie movie,String UserName,String MovieName);

    @Query("CREATE (n:User { id: {id}, name: {name}, age:{age} })")
    User saveUser(@Param("id") Long id,@Param("name")String name,@Param("age")int age);

    //@Query("MATCH (u:User) WHERE u.id={id1} RETURN u")
//    @Query("MATCH (n:User) WHERE id(n)={id1} return n ")
//    User findById(@Param("id1") int id1);
}
