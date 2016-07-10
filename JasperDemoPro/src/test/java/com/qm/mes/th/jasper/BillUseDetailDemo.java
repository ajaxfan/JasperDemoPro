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

import com.qm.mes.th.beans.BillUseDetail;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

public class BillUseDetailDemo {
    /** 模板存放的相对路径 */
    private static final String JASPER_JXML_PATH = "BillUseDetail.jasper";

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

        for (int i = 1; i <= 40; i++) {
            BillUseDetail bean = new BillUseDetail();
            bean.setEmployeeName("测试人员");
            bean.setEmployeeNo("056498785");
            bean.setEnteName("长春南部高速路口");
            bean.setOrderDate("2016-07-10 17:20:32");
            bean.setOrderNo("LOIER897974654");
            bean.setTotalFare(String.valueOf(i * 236));
            
            list.add(bean);
        }
        return list;
    }

    private static JasperReport loadResource() {
        InputStream in = null;
        JasperReport jr = null;

        try {
            // 加载模板文件
            in = BillUseDetailDemo.class.getResourceAsStream(JASPER_JXML_PATH);
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
