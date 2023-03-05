package com.app.mapper;

import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.app.domain.MST_User;

@Repository
public class LoginMapper {
    private NamedParameterJdbcTemplate template;

    //コンストラクタ（初期設定）
    public LoginMapper(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.template = namedParameterJdbcTemplate;
    }

    /**
     * ログインユーザ取得処理　→コメントアウトされてるが無視でOK？
     * 
     * @param username
     * @return Optional<LoginUserForm>
     */
    public Optional<MST_User> findByAccount(String username) {
        StringBuilder sql = new StringBuilder();
        //以下は文字列でSQLを作成している！
        sql.append(
                "SELECT "
                        + "user_address as username "
                        + ",password "
                        + "FROM "
                        + "mst_user "
                        + "WHERE "
                        + "user_address = :username"); //パラメータのほうのusername
        SqlParameterSource prm = new MapSqlParameterSource().addValue("username", username);
        Map<String, Object> result = template.queryForMap(sql.toString(), prm);
        MST_User form = new MST_User();
        form.setUsername((String) result.get("username")); //書き換えた
        form.setPassword((String) result.get("password"));
        return Optional.ofNullable(form);
    }
}
