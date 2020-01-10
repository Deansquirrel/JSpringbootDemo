package com.yuansong.demo.boot.jwt.utils;

import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Base64;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

public class JwtUtils {
	
	private static final String secret = "deansquirrel";
	
	public static String createJWT(String id, String subject, long ttlMillis) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm .HS256;
		long nowMillions = System.currentTimeMillis();
		Date now = new Date(nowMillions);
		SecretKey secretKey = JwtUtils.generalKey();
		JwtBuilder builder = Jwts.builder()
				.setId(id)
				.setSubject(subject)
				.setIssuer("user")
				.setIssuedAt(now)
				.signWith(signatureAlgorithm, secretKey);
		if(ttlMillis >= 0) {
			long expMillis = nowMillions + ttlMillis;
			Date expDate = new Date(expMillis);
			builder.setExpiration(expDate);
		}
		return builder.compact();
	}
	
	public static CheckResult validateJWT(String jwtStr) {
		CheckResult checkResult = new CheckResult();
		Claims claims = null;
		try {
			claims = JwtUtils.parseJWT(jwtStr);
			checkResult.setSuccess(true);
			checkResult.setClaims(claims);
		} catch (ExpiredJwtException e) {
			checkResult.setErrCode(JWT_ERRCODE.FAIL);
			checkResult.setSuccess(false);
		} catch (SignatureException e) {
			checkResult.setErrCode(JWT_ERRCODE.FAIL);
			checkResult.setSuccess(false);
		} catch (Exception e) {
			checkResult.setErrCode(JWT_ERRCODE.FAIL);
			checkResult.setSuccess(false);
		}
		
		return checkResult;
	}
	
	private static SecretKey generalKey() {
		byte[] encodedKey = Base64.decode(JwtUtils.secret);
		SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
		return key;
	}
	
	private static Claims parseJWT(String jwt) throws Exception {
		SecretKey secretKey = JwtUtils.generalKey();
		return Jwts.parser()
				.setSigningKey(secretKey)
				.parseClaimsJws(jwt)
				.getBody();
	}
}
