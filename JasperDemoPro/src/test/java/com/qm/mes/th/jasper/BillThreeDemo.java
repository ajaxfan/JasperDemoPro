package com.qm.mes.th.jasper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;

import com.qm.mes.th.beans.BillThree;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

public class BillThreeDemo {
    /** 模板存放的相对路径 */
    private static final String JASPER_JXML_PATH = "BillThree.jasper";

    public static void main(String[] args) {
        try {
            Map param = new HashMap();

            JasperReport jasper = loadResource();

            print(JasperFillManager.fillReport(jasper, param, new JRBeanCollectionDataSource(createList())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List createList() {
        List list = new ArrayList();

        for (int i = 0; i < 3; i++) {
            BillThree bean = new BillThree();
            bean.setArrears("456798");
            bean.setBillCount("1245679");
            bean.setBillName("LSWER879864564");
            bean.setBillNo("234234-234234, 1231456-897987, 456456-234234");
            bean.setFreeCar("2342234");
            bean.setHarvester("234234");
            bean.setInvalidCard("242344");
            bean.setManageCar("2342342");
            bean.setMilitaryVehicle("45645654");
            bean.setNoCard("4564544");
            bean.setPassCar("5645456");
            bean.setReceivableCard("45645645");
            bean.setReceivableInvalid("234234");
            bean.setReceiveCard("234234");
            bean.setReceiveInvalid("5656565");
            bean.setTotalFare("2222222");
            bean.setUcrossCar("2342342");
            bean.setUrgentCar("1234678");

            list.add(bean);
        }
        return list;
    }

    private static JasperReport loadResource() {
        InputStream in = null;
        JasperReport jr = null;

        try {
            // 加载模板文件
            in = BillThreeDemo.class.getResourceAsStream(JASPER_JXML_PATH);
            // 通过模板来创建Jasper对象
            jr = (JasperReport) JRLoader.loadObject(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    in = null;
                }
            }
        }
        return jr;
    }

    /**
     * 报表打印
     *
     * @param jasperPrint
     * @throws JRException
     */
    private static void print(JasperPrint jasperPrint) throws JRException {
        // 打印请求属性集合
        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
        // 使用A4纸张
        printRequestAttributeSet.add(MediaSizeName.ISO_A4);

        // 实例化Jasper导出工具类
        JRPrintServiceExporter exporter = new JRPrintServiceExporter();
        // 设定导出文件对象
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        // 设定导出工具参数
        exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
        // 设置打印机参数
        exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET,
                new HashPrintServiceAttributeSet());
        // 不显示页面设置对话框
        exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
        // 不显示打印机选择对话框
        exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);

        // 执行打印操作
        exporter.exportReport();
    }
}
