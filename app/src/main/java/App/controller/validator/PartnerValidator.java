package app.controller.validator;

public class PartnerValidator extends CommonsValidator{
    
    public double validAmount(String amount) throws Exception{
        return super.isValidDouble("La cantidad del socio", amount);
    }
     public long validId(String Id) throws Exception{
		return super.isValidLong("el id del socio ", Id);
	}
}
    
