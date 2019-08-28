package com.techprimers.springbootneo4jexample1.repository;

import com.techprimers.springbootneo4jexample1.model.Movie;
import com.techprimers.springbootneo4jexample1.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface MovieRepository extends Neo4jRepository<Movie, Long> {

    @Query("MATCH (m:Movie) RETURN m")
    Collection<Movie> getAllMovies();


//    @Query("MATCH (m:Movie) WHERE m.title ={titles} RETURN m")
//    Collection<Movie> findByTitle(String titles);


    @Query("MATCH (a:User),(b:Movie)\n" +
            "WHERE a.name = {name1} AND b.title = {name2}\n" +
            "CREATE (a)-[r:RATED]->(b)\n" +
            "RETURN type(r)")
    String createRelation(@Param("name1") String name1,@Param("name2")String name2);

    @Query("MATCH (m:Movie) WHERE m.title={title} RETURN m")
    Collection<Movie> findByTitle(@Param("title") String title);

    @Query("MATCH (m:Movie) WHERE m.id={id2} RETURN m")
    Movie getById(Long id2);

    @Query("CREATE (n:Movie { id: {id}, title: {title}, director:{director} })")
    Movie saveMovie(@Param("id") Long id,@Param("title") String title, @Param("director")String director);


//    Movie findByTitle(String title);

//    @Query("MATCH (m:Movie{title:{titles}}) return m")
//    Movie findByTitle(@Param("title") String title);

//    Collection<Movie> saveM()
}
