

public class Converter {

//	@Override
//	public Object getAsObject(FacesContext context, UIComponent component,
//			String value) {
//		
//		Class clazz = getClazz(context, component);
//		this.setEntityClass(clazz);
//		Object annotationFieldValue = ReflectionUtil.getAnnotationValue(
//				clazz, Combox.class, "field");
//		
//		if (annotationFieldValue == null) {
//			return null;
//		}
//		//Busca o nome do campo que é visualizado no combo
//		String fieldName = annotationFieldValue.toString();
//		AbstractEntity entity = this.findUniqueByField(fieldName, value);
//		return entity;
//	}
//
//	
//	@Override
//	public String getAsString(FacesContext context, UIComponent component, Object value) {
//		
//		if (value == null) {
//			return "";
//		}
//		if (value instanceof String) {
//			return (String) value;
//		}
//		if (value != null && !(value instanceof AbstractEntity)) {
//			throw new IllegalArgumentException(
//					"This converter only handles instances of BaseIdentityEntity");
//		}
//		
//		
//		String fieldName = ReflectionUtil.getAnnotationValue(
//				Hibernate.getClass(value), Combox.class, "field")
//				.toString();
//		String result = (String) ReflectionUtil.getFieldValue(value, fieldName);
//		return result;
//	}
}
