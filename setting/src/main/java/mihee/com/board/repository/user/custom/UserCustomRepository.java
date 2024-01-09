package mihee.com.board.repository.user.custom;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import mihee.com.board.controller.user.sdo.UserRdo;
import mihee.com.board.models.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

import static mihee.com.board.models.user.QUser.user;


@Repository
@RequiredArgsConstructor
public class UserCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Page<UserRdo> findAllUserRdo (int page, int size, Sort sort) {
        JPAQuery<User> userQuery = new JPAQuery<>(entityManager);
        JPAQuery<User> countQuery = new JPAQuery<>(entityManager);
        Long count = countQuery.select(Wildcard.count).from(user).fetchOne();
        List<UserRdo> users = userQuery.select(
                Projections.constructor(
                        UserRdo.class,
                        user.id,
                        user.name,
                        user.userNickname,
                        user.userType,
                        user.createdAt
                        )
        )
                .from(user)
                .where()
                .offset(page*size)
                .limit(size)
                .fetch();
        return new PageImpl<>(users, PageRequest.of( (int)Math.ceil((double)page /(double) size), size, sort ), count);
    }
}
