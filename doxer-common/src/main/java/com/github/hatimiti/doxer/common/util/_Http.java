package com.github.hatimiti.doxer.common.util;

import static com.github.hatimiti.doxer.common.util.MIMEType.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * HTTP プロトコルのためのユーティリティ
 * @author hatimiti
 */
public final class _Http {

	/**
	 * private コンストラクタ
	 */
	private _Http() { }

	/**
	 * Httpレスポンスにダウンロードに必要な値をセットし、
	 * Writerオブジェクトを取得する．
	 * @param res Httpサーブレットレスポンス
	 * @param ce 文字コード 列挙型
	 * @throws IOException 入出力例外
	 */
	public static Writer getWriterForDownload(
			final HttpServletResponse res,
			final CharacterEncoding charEnc,
			final MIMEType mime,
			final String fileName) throws IOException {

		setForDownload(res, charEnc, mime, fileName);
		return res.getWriter();
	}

	public static OutputStream getOutputStreamForDownload(
			final HttpServletResponse res,
			final CharacterEncoding charEnc,
			final MIMEType mime,
			final String fileName) throws IOException {
		setForDownload(res, charEnc, mime, fileName);
		return res.getOutputStream();
	}

	private static void setForDownload(
			final HttpServletResponse res,
			final CharacterEncoding charEnc,
			final MIMEType mime,
			final String fileName) {
		res.setCharacterEncoding(charEnc.toString());
		res.setContentType(mime.getValue() + ";");
		res.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		res.setHeader("Expires", "0");
        res.setHeader("Cache-Control", "must-revalidate, post-check=0,pre-check=0");
        res.setHeader("Pragma", "private");
	}

	public static void downloadZip(
			final HttpServletResponse res,
			final CharacterEncoding charEnc,
			final File rootfile,
			final String zipFileName) throws FileNotFoundException, IOException {

		try (ZipOutputStream out = new ZipOutputStream(getOutputStreamForDownload(
				res, charEnc, APPL_OCTET_STREAM, zipFileName))) {
			zip(out, rootfile, rootfile);
		}
	}

	private static void zip(ZipOutputStream zipOutputStream, File rootFile, File targetFile)
			throws FileNotFoundException, IOException {

		if (targetFile.isDirectory()) {
			for (File f : targetFile.listFiles()) {
				zip(zipOutputStream, rootFile, f);
			}
			return;
		}

		ZipEntry zipEntry = new ZipEntry(getZipEntryPath(rootFile, targetFile));
		try (BufferedInputStream inputStream
				= new BufferedInputStream(new FileInputStream(targetFile))) {

			zipOutputStream.putNextEntry(zipEntry);
			writeBufStream(inputStream, zipOutputStream);
			zipOutputStream.closeEntry();
		}
	}

	private static String getZipEntryPath(File rootFile, File targetFile) {
		int lengthToExtractZipPath
			= rootFile.getPath().length() - rootFile.getName().length();
		return targetFile.getPath().replace("\\\\", "/")
				.substring(lengthToExtractZipPath);
	}

	private static void writeBufStream(InputStream inputStream, OutputStream outputStream) throws IOException {
		int availableByteNumber;
		while (0 < (availableByteNumber = inputStream.available())) {
			byte[] buffers = new byte[availableByteNumber];
			int readByteNumber = inputStream.read(buffers);
			if (readByteNumber < 0) {
				break;
			}
			outputStream.write(buffers, 0, readByteNumber);
		}
	}

	public static void write(final OutputStream os, final File file) throws IOException {
		try (
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
				BufferedOutputStream out = new BufferedOutputStream(os)) {

			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			out.flush();
		}
	}

	public static void write(final Writer writer, final File file) throws IOException {
		try (
				BufferedWriter bw = new BufferedWriter(writer);
				BufferedReader br = new BufferedReader(new FileReader(file))) {

			while (br.ready()) {
				bw.write(br.readLine());
				bw.write(_Str.LINE_SEPARATOR);
			}
		}
	}

	public static String getRedirectPath(HttpServletRequest request, String action) {
		String requestUrl = request.getRequestURL().toString();
		String contextPath = request.getContextPath();
		String redirectPath = requestUrl.replaceAll(contextPath + ".*", contextPath + action);
		return redirectPath;
	}

	/**
	 * リクエストヘッダから User-Agent を取得する。
	 * @param request httpリクエスト
	 * @return ユーザーエージェント
	 */
	public static String getUserAgent(HttpServletRequest request) {
		return request.getHeader("User-Agent");
	}

	/**
	 * リクエストヘッダから IPアドレス を取得する。
	 * @param request httpリクエスト
	 * @return IPアドレス
	 */
	public static String getRemoteAddress(HttpServletRequest request) {
		return request.getRemoteAddr();
	}

	/**
	 * セッション固定化攻撃対策用に現在のセッションを新しいセッションに
	 * 保持しているオブジェクトを移し替える。
	 * 現在のセッションは無効化する。
	 * @param request httpリクエスト
	 */
	public static void renewSession4FixationAttack(HttpServletRequest request) {
		Objects.requireNonNull(request);

		HttpSession oldSession = request.getSession();
		Map<String, Object> attrs = new HashMap<>();
		Collections.list(oldSession.getAttributeNames()).forEach(k -> {
			attrs.put(k, oldSession.getAttribute(k));
		});

		oldSession.invalidate();
		HttpSession newSession = request.getSession(true);
		attrs.forEach((k, v) -> { newSession.setAttribute(k, v); });
	}

}
