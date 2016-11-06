package gobyfragment.we.com.gobyfragments;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Convert is to string
 * @author hekeji
 * @since 1.0
 */
public class InputStreamTools {

	public static String covert2String(InputStream input) {

		String convertResult = "";
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		int len = 0;
		byte[] data = new byte[1024];
		try {
			while ((len = input.read(data)) != -1) {
				outputStream.write(data, 0, len);
			}
			convertResult = new String(outputStream.toByteArray(), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null || outputStream != null) {
				try {
					input.close();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return convertResult;

	}

}
