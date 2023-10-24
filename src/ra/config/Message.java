package ra.config;

public class Message {
    public static String getStatusByCode(byte code){
        switch (code){
            case 0 :
                return "Chờ duyệt...";
            case 1:
                return "Xác nhận thành công.";
            case 2:
                return "Hủy đơn hàng";
            default:
                return  "Không hợp lệ.";
        }
    }
}
