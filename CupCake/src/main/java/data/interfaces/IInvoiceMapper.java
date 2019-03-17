/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.interfaces;

import java.sql.SQLException;
import java.util.List;
import logic.model.Invoice;
import logic.model.ShoppingCart;

/**
 *
 * @author Martin Frederiksen
 */
public interface IInvoiceMapper {
    void addInvoice(int orderId, ShoppingCart sc) throws SQLException;   
    List<Invoice> getInvoices() throws SQLException;
    Invoice getInvoiceById(int invoiceId) throws SQLException;
}
