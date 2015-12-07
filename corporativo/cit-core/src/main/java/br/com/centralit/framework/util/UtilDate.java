package br.com.centralit.framework.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import org.apache.commons.lang.time.DateUtils;

import br.com.centralit.framework.exception.BusinessException;
import br.com.centralit.framework.exception.InfrastructureException;

public class UtilDate {
	public static Timestamp timestamp(int year, int month, int day) {
		Calendar cal = calendar(year, month, day);
		return calendarToTimestamp(cal);
	}

	public static boolean isAnoIgual(Calendar dataComparativa, Calendar dataReferenciaVigente) {

		return dataComparativa.get(Calendar.YEAR) == dataReferenciaVigente.get(Calendar.YEAR);
	}

	public static boolean isMesIgual(Calendar data1, Calendar data2) {

		return data1.get(Calendar.MONTH) == data2.get(Calendar.MONTH);
	}

	public static boolean isMesmaData(Calendar primeiraData, Calendar segundaData) {
		SimpleDateFormat dateFormatPrimeiraData = new SimpleDateFormat("dd/MM/yyyy ");
		SimpleDateFormat dateFormatSegundaData = new SimpleDateFormat("dd/MM/yyyy ");
		return dateFormatPrimeiraData.format(primeiraData.getTime()).equals(dateFormatSegundaData.format(segundaData.getTime()));
	}

	public static boolean isPrimeiraDataMenorSegundaData(Calendar primeiraData, Calendar segundaData) {
		return !isMesmaData(primeiraData, segundaData) && primeiraData.before(segundaData);
	}

	public static Timestamp getDataAtual() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static Calendar getDataAtualCalendar() {
		return timestampToCalendar(new Timestamp(System.currentTimeMillis()));
	}

	public static Calendar getDataAtualCalendarSemMilisegundos() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}

	public static Timestamp getDataAtualUltimaHora() {
		return timestampToTimestampUltimaHoraDoDia(new Timestamp(
				new Date().getTime()));
	}

	public static Timestamp dateToTimestamp(Date date) {
		Calendar cal = timestampToCalendar(new Timestamp(date.getTime()));
		return new Timestamp(cal.getTimeInMillis());
	}

	public static void setTimeZone() {
		SimpleTimeZone stz = new SimpleTimeZone(-10800000, "GMT-2:00");
		stz.setStartRule(9, 3, 1, 0);
		stz.setEndRule(1, 3, 1, 0);
		TimeZone.setDefault(stz);
	}

	public static Calendar calendar(int year, int month, int day) {
		return new GregorianCalendar(year, month - 1, day);
	}

	public static Date stringToDate(String value) {
		String[] data = value.split("/");
		return new Date(data[1] + "/" + data[0] + "/" + data[2]);
	}

	public static Date stringReferenciaToDate(String value) {
		String[] data = value.split("/");
		return new Date(data[0] + "/" + 1 + "/" + data[1]);
	}

	public static Timestamp timestampToTimestampDate(Timestamp data) {
		Calendar cal = timestampToCalendar(data);
		cal.set(10, 0);
		cal.set(11, 0);
		cal.set(12, 0);
		cal.set(13, 0);
		cal.set(14, 0);
		return calendarToTimestamp(cal);
	}

	public static Timestamp timestampDateOnly() {
		Calendar cal = new GregorianCalendar();
		cal.set(10, 0);
		cal.set(11, 0);
		cal.set(12, 0);
		cal.set(13, 0);
		cal.set(14, 0);
		return calendarToTimestamp(cal);
	}

	public static Timestamp dateToTimestampApenasData(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.set(5, Integer.parseInt(getDia(date)));
		cal.set(2, Integer.parseInt(getMes(date)));
		cal.set(1, Integer.parseInt(getAno(date)));
		return calendarToTimestamp(cal);
	}

	public static Timestamp timestampDateOnly(Timestamp data) {
		Calendar cal = data == null ? new GregorianCalendar()
				: timestampToCalendar(data);
		cal.set(10, 0);
		cal.set(11, 0);
		cal.set(12, 0);
		cal.set(13, 0);
		cal.set(14, 0);
		return calendarToTimestamp(cal);
	}

	public static Timestamp timestampToTimestampUltimaHoraDoDia(Timestamp data) {
		Calendar cal = timestampToCalendar(data);
		cal.set(11, 0);
		cal.set(12, 0);
		cal.set(13, 0);
		cal.set(14, 0);
		cal.add(5, 1);
		cal.add(13, -1);
		return calendarToTimestamp(cal);
	}

	public static boolean isUltimoDiaMes(Timestamp data) {
		Calendar calData = timestampToCalendar(data);
		Calendar calDataMaisUm = timestampToCalendar(data);
		calDataMaisUm.add(5, 1);
		return calData.get(2) != calDataMaisUm.get(2);
	}

	public static boolean compareCalendar(Calendar data, Calendar data2) {
		return (data.get(5) == data2.get(5)) && (data.get(2) == data2.get(2))
				&& (data.get(1) == data2.get(1));
	}

	public static Date stringTimestampToDate(String value) {
		Date dataReturn = null;
		try {
			if (value.length() > 10) {
				DateFormat data = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				dataReturn = data.parse(value);
			} else {
				DateFormat data = new SimpleDateFormat("dd/MM/yyyy");
				dataReturn = data.parse(value);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dataReturn;
	}

	public static Date stringTimestampToDateApenasData(String value) {
		Date dataReturn = null;
		try {
			DateFormat data = new SimpleDateFormat("dd/MM/yyyy");
			dataReturn = data.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dataReturn;
	}

	public static Timestamp todayNotTime() {
		Calendar dataHora = new GregorianCalendar();
		Calendar data = new GregorianCalendar(dataHora.get(1), dataHora.get(2),
				dataHora.get(5));
		return new Timestamp(data.getTimeInMillis());
	}

	public static Calendar timestampToGregorianCalendar(Timestamp data) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(data);
		return calendar;
	}

	public static String getPaternTimestamp(Timestamp data, String patern) {
		String dataReturn = "";
		try {
			DateFormat df = new SimpleDateFormat(patern);
			dataReturn = df.format(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataReturn;
	}

	public static int getIdade(String dataNasc) {
		String[] data = dataNasc.contains("/") ? dataNasc.split("/") : dataNasc
				.split("-");

		Calendar dateOfBirth = new GregorianCalendar(
				Integer.parseInt(data[2]),
				Integer.parseInt(data[1]) - 1,
				Integer.parseInt(data[0]));

		Calendar today = Calendar.getInstance();

		int age = today.get(1) - dateOfBirth.get(1);

		dateOfBirth.add(1, age);
		if (today.before(dateOfBirth)) {
			age--;
		}
		return age;
	}

	public static int getIdade(Timestamp dataNasc) {
		String[] data = dataNasc.toString().split("-");
		data[2] = data[2].substring(0, 2).trim();

		Calendar dateOfBirth = new GregorianCalendar(
				Integer.parseInt(data[0]),
				Integer.parseInt(data[1]) - 1,
				Integer.parseInt(data[2]));

		Calendar today = Calendar.getInstance();

		int age = today.get(1) - dateOfBirth.get(1);

		dateOfBirth.add(1, age);
		if (today.before(dateOfBirth)) {
			age--;
		}
		return age;
	}

	public static int getIdade(Timestamp dataNasc, Timestamp dataReferencia) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String[] data = formatter.format(dataNasc).split("-");
		String[] dataRef = formatter.format(dataReferencia).split("-");

		Calendar dateOfBirth = new GregorianCalendar(
				Integer.parseInt(data[0]),
				Integer.parseInt(data[1]) - 1,
				Integer.parseInt(data[2]));

		Calendar today = new GregorianCalendar(
				Integer.parseInt(dataRef[0]),
				Integer.parseInt(dataRef[1]) - 1,
				Integer.parseInt(dataRef[2]));

		int age = today.get(1) - dateOfBirth.get(1);

		dateOfBirth.add(1, age);
		if (today.before(dateOfBirth)) {
			age--;
		}
		return age;
	}

	public static int getIdadeAddYear(Timestamp dataNasc, int qtdAnos) {
		String[] data = dataNasc.toString().split("-");
		data[2] = data[2].substring(0, 2).trim();

		Calendar dateOfBirth = new GregorianCalendar(
				Integer.parseInt(data[0]),
				Integer.parseInt(data[1]) - 1,
				Integer.parseInt(data[2]));

		Calendar today = Calendar.getInstance();
		today.add(1, qtdAnos);

		int age = today.get(1) - dateOfBirth.get(1);

		dateOfBirth.add(1, age);
		if (today.before(dateOfBirth)) {
			age--;
		}
		return age;
	}

	public static Calendar timestampToCalendar(Timestamp ts) {
		if (ts == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();

		setTimeZone();

		cal.setTimeInMillis(ts.getTime());
		return cal;
	}

	public static Calendar dateToCalendar(Date date) {

		Calendar cal = Calendar.getInstance();

		cal.setTime(date);

		return cal;
	}

	public static Timestamp calendarToTimestamp(Calendar cal) {
		if (cal == null) {
			return null;
		}
		Timestamp ts = new Timestamp(cal.getTimeInMillis());
		return ts;
	}

	public static Timestamp timestampAdd(Timestamp ts, int calendarField,
			int amount) {
		Calendar cal = timestampToCalendar(ts);
		cal.add(calendarField, amount);
		return new Timestamp(cal.getTimeInMillis());
	}

	public static long diferencaMeses(Timestamp dataInicial, Timestamp dataFinal) {
		return (dataFinal.getTime() / 24L / 60L / 60L / 1000L - dataInicial
				.getTime() / 24L / 60L / 60L / 1000L)
				/ ((long)365.25 / 12L);
	}

	public static long diferencaDias(Date dataInicial, Date dataFinal) {
		return dataFinal.getTime() / 24L / 60L / 60L / 1000L
				- dataInicial.getTime() / 24L / 60L / 60L / 1000L;
	}

	public static long diferencaHoras(Date dataInicial, Date dataFinal) {
		return dataInicial.getTime() / 60L / 60L / 1000L - dataFinal.getTime()
				/ 24L / 60L / 60L / 1000L;
	}

	public static long diferencaDeDatasEmHoras(Date dataInicial, Date dataFinal) {
		return dataInicial.getTime() / 60L / 60L / 1000L - dataFinal.getTime()
				/ 60L / 60L / 1000L;
	}

	public static long diferencaMinutos(Date dataInicial, Date dataFinal) {
		return dataInicial.getTime() / 60L / 1000L - dataFinal.getTime() / 24L / 60L / 60L / 1000L;
	}

	public static long diferencaSegundos(Timestamp dataInicial,
			Timestamp dataFinal) {
		return dataInicial.getTime() / 1000L - dataFinal.getTime() / 24L / 60L
				/ 60L / 1000L;
	}

	public static int getDiferencaDeHorariosEmMinutos(String horarioInicial, String horarioFinal) {
		return getQuantidadeDeMinutos(horarioFinal) - getQuantidadeDeMinutos(horarioInicial);
	}

	public static String somaQuantidadeDeTempo(String valor1, String valor2) {
		valor1 = valor1.trim();
		valor2 = valor2.trim();
		int minutos = Integer.parseInt(valor1.substring(valor1.length() - 2,
				valor1.length()));
		minutos += Integer.parseInt(valor2.substring(valor2.length() - 2,
				valor2.length()));

		int tamanho1 = valor1.length() - 3;
		int tamanho2 = valor2.length() - 3;

		int horas = tamanho1 >= 0 ? Integer.parseInt(valor1.substring(0,
				tamanho1)) : 0;
		horas += (tamanho2 >= 0 ? Integer.parseInt(valor2
				.substring(0, tamanho2)) : 0);
		horas += minutos / 60;
		minutos %= 60;
		String minutosFormatados = Integer.toString(minutos);
		if (minutosFormatados.length() < 2) {
			minutosFormatados = "0" + minutosFormatados;
		}
		return horas + ":" + minutosFormatados;
	}

	public static int getQuantidadeDeMinutos(String horario) {
		String[] horarioSplit = horario.split(":");
		String minutos = horarioSplit.length > 1 ? horarioSplit[1] : horarioSplit[0];

		if (minutos.startsWith("0")) {
			minutos = minutos.substring(1);
		}

		int qtdMinutos = minutos.equals("") ? 0 : Integer.parseInt(minutos);
		String horas = horarioSplit[0];

		if (horas.startsWith("0")) {
			horas = horas.substring(1);
		}

		int qtdHoras = horas.equals("") ? 0 : Integer.parseInt(horas);

		return qtdHoras * 60 + qtdMinutos;
	}

	public static long getMinutos(String hora, SimpleDateFormat formatter) {
		Date data;
		try {
			data = formatter.parse(hora);
		} catch (ParseException e) {
			return 0L;
		}
		return data.getTime() / 1000L / 60L;
	}

	public static String subtraiHora(String hora, String hora2) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));

		long min_1 = getMinutos(hora, formatter);
		long min_2 = getMinutos(hora2, formatter);
		long result = (min_1 - min_2) * 60L * 1000L;
		Date data = new Date(result);
		return formatter.format(data);
	}

	public static String multiplicaHora(String hora, int quantidade) {
		long min_1 = getQuantidadeDeMinutos(hora);
		long result = min_1 * quantidade;
		String minutos = Long.toString(result % 60L);
		long horas = Math.abs(result / 60L);
		return horas + ":" + (minutos.length() == 1 ? "0" + minutos : minutos);
	}

	public static String subtraiHorasGrandes(String hora, String hora2) {
		long min_1 = getQuantidadeDeMinutos(hora);
		long min_2 = getQuantidadeDeMinutos(hora2);
		long result = min_1 - min_2;
		String minutos = Long.toString(result % 60L);
		long horas = Math.abs(result / 60L);
		return horas
				+ ":"
				+ (minutos.length() == 0 ? "00" : minutos.length() == 1 ? "0"
						+ minutos : minutos);
	}

	public static String getHoras(Date data) {
		return formatarData(data, "HH:mm");
	}

	public static String getHoraAtual() {
		return formatarData(getDataAtual(), "HH:mm");
	}

	public static String getMes(Date data) {
		return formatarData(data, "MM");
	}

	public static String getAno(Date data) {
		return formatarData(data, "yyyy");
	}

	public static String getDia(Date data) {
		return formatarData(data, "dd");
	}

	public static String getHorasComSegundos(Date data) {
		return formatarData(data, "hh:mm:ss a");
	}

	public static String getHorasComSegundos24Horas(Date data) {
		return formatarData(data, "HH:mm:ss");
	}

	public static String getDiaDaSemanaDataEHora() {
		return formatarData(getDataAtual(),
				"EEEE, d 'de' MMMMM 'de' yyyy '�s' HH:mm:ss");
	}

	public static String getDiaDaSemanaEData() {
		return formatarData(getDataAtual(), "EEEE, d 'de' MMMMM 'de' yyyy");
	}

	public static String getDiaDaSemanaEData(Date data) {
		return formatarData(data, "EEEE, d 'de' MMMMM 'de' yyyy");
	}

	public static String getDiaDaSemanaEData(Timestamp data) {
		return formatarData(stringTimestampToDate(data.toString()),
				"EEEE, d 'de' MMMMM 'de' yyyy");
	}

	public static String getDataExtenso() {
		return formatarData(getDataAtual(), "d 'de' MMMMM 'de' yyyy");
	}

	public static String getDataExtenso(Date data) {
		return formatarData(data, "d 'de' MMMMM 'de' yyyy");
	}

	public static Date getDataSeguinte(Date data) {
		Timestamp timestamp = new Timestamp(data.getTime());
		Calendar cal = timestampToCalendar(timestamp);
		cal.add(5, 1);
		return calendarToTimestamp(cal);
	}

	public static Date getDataAnterior(Date data) {
		Timestamp timestamp = new Timestamp(data.getTime());
		Calendar cal = timestampToCalendar(timestamp);
		cal.add(5, -1);
		return calendarToTimestamp(cal);
	}

	public static Date getPrimeiroDiaDaPrimeiraSemanaDoAno(int ano) {
		Calendar calendar2 = new GregorianCalendar(ano, 0, 1);
		switch (calendar2.get(7)) {
		case 2:
			calendar2.add(5, 0);
			break;
		case 3:
			calendar2.add(5, -1);
			break;
		case 4:
			calendar2.add(5, -2);
			break;
		case 5:
			calendar2.add(5, -3);
			break;
		case 6:
			calendar2.add(5, -4);
			break;
		case 7:
			calendar2.add(5, -5);
			break;
		case 1:
			calendar2.add(5, -6);
			break;
		default:
			break;
		}
		return calendar2.getTime();
	}

	public static Date getUltimoDiaDaUltimaSemanaDoAno(int ano) {
		Calendar data = dateToCalendar(getPrimeiroDiaDaPrimeiraSemanaDoAno(ano + 1));
		data.add(5, -1);
		return data.getTime();
	}

	public static Timestamp getDataAtualSemHoras() {
		try {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			String dataStr = format.format(Long.valueOf(new Date().getTime()));
			return new Timestamp(format.parse(dataStr).getTime());
		} catch (Throwable e) {
			throw new InfrastructureException(e);
		}
	}

	public static Date getDataSemHoras(Date data) {
		try {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			String dataStr = format.format(data);
			return format.parse(dataStr);
		} catch (Throwable e) {
			throw new InfrastructureException(e);
		}
	}

	public static String getDataSemHorasString(Date data) {
		try {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			return format.format(data);
		} catch (Throwable e) {
			throw new InfrastructureException(e);
		}
	}

	public static String getAnoDeData(Date data) {
		return new SimpleDateFormat("yyyy").format(data);
	}

	public static String formatarHora(String hora) {
		try {
			if ((hora != null) && (!hora.equals(""))) {
				String horas = hora.substring(0, 2);
				String minutos = hora.substring(2, 4);
				return horas + ":" + minutos;
			}
		} catch (IndexOutOfBoundsException e) {
			return hora;
		}
		return "  :  ";
	}

	public static List<Date> getDatasDoIntevalo(Date dataInicial, Date dataFinal) {
		List<Date> datasDoIntervalo = new ArrayList();
		datasDoIntervalo.add(dataInicial);
		Long diferencaEntreDias = Long.valueOf(diferencaDias(dataFinal,
				dataInicial));
		for (int i = 0; i < diferencaEntreDias.longValue(); i++) {
			Date data = getDataSeguinte((Date) datasDoIntervalo
					.get(datasDoIntervalo.size() - 1));
			datasDoIntervalo.add(data);
		}
		return datasDoIntervalo;
	}

	public static Integer getQuantidadeDeDiasNoIntervaloDeDatas(
			Date dataInicial, Date dataFinal) {
		return Integer.valueOf(getDatasDoIntevalo(dataInicial, dataFinal)
				.size());
	}

	public static int ultimoDiaDoMes(Calendar calendar) throws Exception {
		calendar.add(2, 1);
		calendar.set(5, 1);
		calendar.add(5, -1);

		return calendar.get(5);
	}

	public static Timestamp dataUltimoDiaDoMes(Calendar calendar)
			throws Exception {
		calendar.add(2, 1);
		calendar.set(5, 1);
		calendar.add(5, -1);

		return calendarToTimestamp(calendar);
	}

	public static Calendar calendarUltimoDiaDoMes(Calendar calendar){
		Calendar ultimoDiaMes = (Calendar) calendar.clone();
		ultimoDiaMes.set(Calendar.DAY_OF_MONTH, ultimoDiaMes.getActualMaximum(Calendar.DAY_OF_MONTH));
	    return ultimoDiaMes;
	}

	public static Calendar calendarUltimoDiaDoMesUltimaHora(Calendar calendar){
		Calendar ultimoDiaMes = (Calendar) calendar.clone();
		ultimoDiaMes.set(Calendar.DAY_OF_MONTH, ultimoDiaMes.getActualMaximum(Calendar.DAY_OF_MONTH));

		Timestamp ultimaDiaMesTimestamp = calendarToTimestamp(ultimoDiaMes);
		ultimaDiaMesTimestamp = timestampToTimestampUltimaHoraDoDia(ultimaDiaMesTimestamp);
		ultimoDiaMes = timestampToCalendar(ultimaDiaMesTimestamp);

		return ultimoDiaMes;
	}

	public static String formatarData(Date data) {
		return formatarData(data, "dd/MM/yyyy");
	}

	public static String formatarDataEntreVirgula(Date data) {
		return formatarData(data, "yyyy, MM, dd");
	}

	public static String formatarDataParaMesAno(Date data) {
		return formatarData(data, "MM/yyyy");
	}

	public static String formatarData(Date data, String formato) {
		return new SimpleDateFormat(formato).format(data);
	}

	public static Date getData(String valor) {
		try {
			String[] formatos = { "HH:mm", "dd/MM/yyyy", "dd/MM/yyyy HH:mm",
					"dd/MM/yyyy HH:mm:ss", "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss",
					"yyyy-MM-dd HH:mm:ss.S", "EEE MMM dd HH:mm:ss z yyyy" };

			return DateUtils.parseDate(converterDiaDaSemanaEMes(valor),
					formatos);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected static String converterDiaDaSemanaEMes(String valor) {
		String resultado = converterDiaDaSemana(valor);
		return converterMes(resultado);
	}

	protected static String converterMes(String valor) {
		valor = trocarValorSeNecessario(valor, "Feb", "Fev");
		valor = trocarValorSeNecessario(valor, "Apr", "Abr");
		valor = trocarValorSeNecessario(valor, "May", "Mai");
		valor = trocarValorSeNecessario(valor, "Aug", "Ago");
		valor = trocarValorSeNecessario(valor, "Sep", "Set");
		valor = trocarValorSeNecessario(valor, "Oct", "Out");
		valor = trocarValorSeNecessario(valor, "Dec", "Dez");
		return valor;
	}

	protected static String converterDiaDaSemana(String valor) {
		valor = trocarValorSeNecessario(valor, "Mon", "Seg");
		valor = trocarValorSeNecessario(valor, "Tue", "Ter");
		valor = trocarValorSeNecessario(valor, "Wed", "Qua");
		valor = trocarValorSeNecessario(valor, "Thu", "Qui");
		valor = trocarValorSeNecessario(valor, "Fri", "Sex");
		valor = trocarValorSeNecessario(valor, "Sat", "S\u00e1b");
		valor = trocarValorSeNecessario(valor, "Sun", "Dom");
		return valor;
	}

	protected static String trocarValorSeNecessario(String valor,
			String expressao1, String expressao2) {
		if (valor.indexOf(expressao1) > -1) {
			valor = valor.replaceAll(expressao1, expressao2);
		}
		return valor;
	}

	public static String adicionaSubtraiData(Long quantidadeDias, Date data) {
		DateFormat converteData = DateFormat.getDateInstance(2);
		return converteData
				.format(adicionarDiasADataAtual(quantidadeDias, data));
	}

	public static Date adicionarDiasADataAtual(Long quantidadeDias, Date data) {
		Calendar cal = GregorianCalendar.getInstance();
		Date dataConvertida = null;
		String dataString = "";
		DateFormat converteData = DateFormat.getDateInstance(2);
		dataString = converteData.format(data);
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		try {
			dataConvertida = formatador.parse(dataString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (dataConvertida != null) {
			cal.setTime(dataConvertida);
			cal.add(5, quantidadeDias.intValue());
			dataConvertida = cal.getTime();
		}
		return dataConvertida;
	}

	public static Date adicionarMesesADataAtual(Long quantidadeMeses, Date data) {
		Calendar cal = GregorianCalendar.getInstance();
		String dataString = "";
		DateFormat converteData = DateFormat.getDateInstance(2);
		dataString = converteData.format(data);
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date dataConvertida = formatador.parse(dataString);
			cal.setTime(dataConvertida);
			cal.add(2, quantidadeMeses.intValue());
			data = cal.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return data;
	}

	public static Date adicionarAnosADataAtual(Integer quantidadeAnos, Date data) {
		Calendar cal = GregorianCalendar.getInstance();
		String dataString = "";
		DateFormat converteData = DateFormat.getDateInstance(2);
		dataString = converteData.format(data);
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date dataConvertida = formatador.parse(dataString);
			cal.setTime(dataConvertida);
			cal.add(1, quantidadeAnos.intValue());
			data = cal.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return data;
	}

	public static Date adicionarAnosADataAtual(Integer quantidadeAnos) {
		return adicionarAnosADataAtual(quantidadeAnos, getDataAtual());
	}

	public static Date adicionarMesesADataAtual(Integer quantidadeMeses,
			Date data) {
		return adicionarMesesADataAtual(Long.valueOf(quantidadeMeses.intValue()), data);
	}

	public static Date somaHoras(Date data, int quantidadeDeHoras) {
		return somaCampo(11, data, quantidadeDeHoras);
	}

	public static Date somaMinutos(Date data, int quantidadeDeMinutos) {
		return somaCampo(12, data, quantidadeDeMinutos);
	}

	public static Date somaMilisegundos(Date data, int quantidadeDeMilisegundos) {
		return somaCampo(14, data, quantidadeDeMilisegundos);
	}

	public static Date somaSegundos(Date data, int quantidadeDeSegundos) {
		return somaCampo(13, data, quantidadeDeSegundos);
	}

	public static Date somaDias(Date data, int quantidadeDeDias) {
		return somaCampo(5, data, quantidadeDeDias);
	}

	public static Timestamp somaDiasTimestamp(Date data, int quantidadeDeDias) {
		return dateToTimestamp(somaCampo(5, data, quantidadeDeDias));
	}

	public static Date somaMeses(Date data, int numeroDeMeses) {
		return somaCampo(2, data, numeroDeMeses);
	}

	public static Date somaAnos(Date data, int numeroDeAnos) {
		return somaCampo(1, data, numeroDeAnos);
	}

	public static Date somaCampo(int campo, Date data, int quantidade) {
		Calendar calendar = getCalendarDaData(data);
		calendar.add(campo, quantidade);
		return calendar.getTime();
	}

	public static Calendar getCalendarDaDataAtual() {
		return getCalendarDaData(getDataAtual());
	}

	public static Calendar getCalendarDaData(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar;
	}

	public static boolean isDataDoMesAtual(Date data) {
		return isDataDoMes(data, getCalendarDaDataAtual().get(2));
	}

	public static boolean isDataDoMes(Date data, int mes) {
		Calendar calendar = getCalendarDaData(data);
		return calendar.get(2) == mes;
	}

	public static String getMesAnoAtual() {
		return formatarData(getDataAtual(), "MM/yyyy");
	}

	public static String getMesAtual() {
		return formatarData(getDataAtual(), "MM");
	}

	public static String getDiaAtual() {
		return formatarData(getDataAtual(), "dd");
	}

	public static String getAnoAtual() {
		return formatarData(getDataAtual(), "yyyy");
	}

	public static String getMesAno(Date data) {
		return formatarData(data, "MM/yyyy");
	}

	public static String adicionarMinutos(String horario, Integer minutos) {
		Date data = getData(horario);
		data = somaMinutos(data, minutos.intValue());
		return getHoras(data);
	}

	public static Date diaNaSemanaAnterior() {
		Calendar calendar = new GregorianCalendar();
		calendar.add(5, -7);
		return calendar.getTime();
	}

	public static long getDataComoJulianDate(Date data) {
		return diferencaDias(getData("-13/01/0000"), data);
	}

	public static Date timestampToDate(Timestamp data) {
		Calendar cal = timestampToCalendar(data);
		cal.set(10, 0);
		cal.set(11, 0);
		cal.set(12, 0);
		cal.set(13, 0);
		cal.set(14, 0);
		return calendarToDate(cal);
	}

	public static Date calendarToDate(Calendar cal) {
		if (cal == null) {
			return null;
		}
		Date date = new Date(cal.getTimeInMillis());
		return date;
	}

	public static int getDiaDaSemanaDoCalendar(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar.get(7);
	}

	public static Integer getQuantidadeDeHorasUtieisDoIntervaloDeDatas(
			Date dataInicial, Date dataFinal) {
		Integer quantidadeDeHorasUties = Integer.valueOf(0);
		while (dataInicial.getTime() <= dataFinal.getTime()) {
			int diaDaSemana = getDiaDaSemanaDoCalendar(dataInicial);
			boolean isDiaUtil = (diaDaSemana != 1) && (diaDaSemana != 7);
			if (isDiaUtil) {
				quantidadeDeHorasUties = Integer.valueOf(quantidadeDeHorasUties.intValue() + 8);
			}
			dataInicial = adicionarDiasADataAtual(Long.valueOf(1), dataInicial);
		}
		return quantidadeDeHorasUties;
	}

	public static Timestamp getDataHora(String data, String hora) {
		return atribuirHoraCompletaAData(dateToTimestamp(getData(data)), hora);
	}

	public static Timestamp atribuirHoraAData(Timestamp data, String horario) {
		data = data != null ? data : getDataAtual();
		horario = (horario == null) || (horario.equals("")) ? "00:00" : horario;

		String hora = horario.split(":")[0];
		String minuto = horario.split(":")[1].split(" ")[0];
		String am_pm = horario.split(":")[1].split(" ").length > 1 ? horario
				.split(":")[1].split(" ")[1] : "";

		Calendar cal = timestampToCalendar(data);
		if (am_pm.equals("PM")) {
			cal.set(11, Integer.parseInt(hora) + 12);
		} else {
			cal.set(11, Integer.parseInt(hora));
		}
		cal.set(12, Integer.parseInt(minuto));

		return calendarToTimestamp(cal);
	}

	public static Timestamp atribuirHoraCompletaAData(Timestamp data,
			String horario) {
		data = data != null ? data : getDataAtual();
		horario = (horario == null) || (horario.equals("")) ? "00:00:00"
				: horario;

		String[] horarioArray = horario.split(":");

		Calendar cal = timestampToCalendar(data);
		cal.set(11, Integer.parseInt(horarioArray[0]));
		cal.set(12, Integer.parseInt(horarioArray[1]));
		cal.set(13, Integer.parseInt(horarioArray[2]));

		return calendarToTimestamp(cal);
	}

	public static Integer getQuantidadeDeHorasCheiasDoIntervaloDeDatas(
			Date dataInicial, Date dataFinal) {
		Integer quantidadeDeHoras = Integer.valueOf(0);
		while ((dataInicial != null)
				&& (dataInicial.getTime() <= dataFinal.getTime())) {
			int diaDaSemana = getDiaDaSemanaDoCalendar(dataInicial);
			boolean isDiaUtil = (diaDaSemana != 1) && (diaDaSemana != 7);
			if (isDiaUtil) {
				quantidadeDeHoras = Integer.valueOf(quantidadeDeHoras
						.intValue() + 24);
			}
			dataInicial = adicionarDiasADataAtual(Long.valueOf(1), dataInicial);
		}
		return quantidadeDeHoras;
	}

	public static String formataHora(BigDecimal horas) {
		String[] string = horas.toString().replace(".", ",").split(",");
		String horaFormatada = string[0]
				+ ":"
				+ (Long.parseLong(string[1]) * 60 / 100 == 0 ? "00"
						: new StringBuilder()
								.append("")
								.append(Long.parseLong(string[1]) * 60 / 100).toString());
		return horaFormatada;
	}

	public static boolean isMesAnoMaiorQueAtual(Integer mes, Integer ano) {
		Integer mesAtual = Integer.valueOf(getMesAtual());
		Integer anoAtual = Integer.valueOf(getAnoAtual());
		int resultadoAno = ano.compareTo(anoAtual);
		int resultadoMes = mes.compareTo(mesAtual);
		if (resultadoAno > 0) {
			return true;
		}
		if ((resultadoAno == 0) && (resultadoMes > 0)) {
			return true;
		}
		return false;
	}

	public static Date getPrimeiroDiaDoAno(Number ano) {
		return getData("01/01/" + ano);
	}

	public static Date getUltimoDiaDoAno(Number ano) {
		return getData("31/12/" + ano);
	}

	public static boolean isDataMenorQueDataAtual(Date data) {
		return isDataMenor(data, getDataAtual());
	}

	public static boolean isDataMenor(Date dataInicial, Date dataFinal) {
		return diferencaDias(dataInicial, dataFinal) < 0L;
	}

	public static Date getPrimeiroDiaDoMes(Date data) {
		setTimeZone();
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		return getPrimeiroDiaDoMes(Integer.valueOf(cal.get(2) + 1),
				Integer.valueOf(cal.get(1)));
	}

	public static Date getPrimeiroDiaDoMes(Integer mes, Integer ano) {
		return getData("01/" + mes + "/" + ano);
	}

	public static Date getDataUltimoDiaDoMes(Date data) throws Exception {
		setTimeZone();
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		return getDataUltimoDiaDoMes(Integer.valueOf(cal.get(2) + 1),
				Integer.valueOf(cal.get(1)));
	}

	public static Date getDataUltimoDiaDoMes(Integer mes, Integer ano)
			throws Exception {
		return dataUltimoDiaDoMes(getCalendarDaData(getPrimeiroDiaDoMes(mes,
				ano)));
	}

	public static int getUltimoDiaDoMes(Date data)
			throws NumberFormatException, Exception {
		Date dia = getDataUltimoDiaDoMes(
				Integer.valueOf(Integer.parseInt(getMes(data))),
				Integer.valueOf(Integer.parseInt(getAno(data))));

		return Integer.parseInt(getDia(dia));
	}

	public static int getPrimeiroDiaDoMes(Calendar data) {

		return data.getActualMinimum(Calendar.DAY_OF_MONTH);
	}

	public static int getUltimoDiaDoMes(Calendar data) {

		return data.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static Calendar getMesPrimeiroDia(Calendar data) {

		data.set(Calendar.DAY_OF_MONTH, data.getActualMinimum(Calendar.DAY_OF_MONTH));

		return data;
	}

	public static Calendar getMesUltimoDia(Calendar data) {

		data.set(Calendar.DAY_OF_MONTH, data.getActualMaximum(Calendar.DAY_OF_MONTH));

		return data;
	}

	public static int getUltimoDiaDoMes(Integer mes, Integer ano)
			throws NumberFormatException, Exception {
		Date dia = dataUltimoDiaDoMes(getCalendarDaData(getPrimeiroDiaDoMes(
				mes, ano)));
		return Integer.parseInt(getDia(dia));
	}

	public static List<Date> getPrimeirosDiasDosMesesDoIntervalo(
			Date dataInicial, Date dataFinal) {
		List<Date> datas = new ArrayList();
		Date primeiroDiaDoUltimoMes = getPrimeiroDiaDoMes(
				Integer.valueOf(Integer.parseInt(getMes(dataFinal))),
				Integer.valueOf(Integer.parseInt(getAno(dataFinal))));
		Date primeiroDiaDoPrimeiro = getPrimeiroDiaDoMes(
				Integer.valueOf(Integer.parseInt(getMes(dataInicial))),
				Integer.valueOf(Integer.parseInt(getAno(dataInicial))));
		datas.add(primeiroDiaDoPrimeiro);
		while (isDataMenor(primeiroDiaDoPrimeiro, primeiroDiaDoUltimoMes)) {
			Date data = adicionarMesesADataAtual(Integer.valueOf(1),
					(Date) datas.get(datas.size() - 1));
			primeiroDiaDoPrimeiro = data;
			datas.add(data);
		}
		return datas;
	}

	public static Date montaDataInicioMes(Long mes, Long ano) {
		return stringToDate("01/" + mes + "/" + ano);
	}

	public static long getDiferencaDeHorariosEmSegundos(String horaInicial,
			String horaFinal) {
		String[] arrayHoraInicial = horaInicial.split(":");
		String[] arrayHoraFinal = horaFinal.split(":");

		long segundosInicial = Long.parseLong(arrayHoraInicial[0]) * 60L
				* 60L + Long.parseLong(arrayHoraInicial[1]) * 60L
				+ Long.parseLong(arrayHoraInicial[2]);
		long segundosFinal = Long.parseLong(arrayHoraFinal[0]) * 60L
				* 60L + Long.parseLong(arrayHoraFinal[1]) * 60L
				+ Long.parseLong(arrayHoraFinal[2]);

		return segundosFinal - segundosInicial;
	}

	public static boolean verificaSeDataSuperiorADiasUteis(Timestamp data,
			Long qtdDiaUteis) {
		if (data.before(getDataAtualSemHoras())) {
			return false;
		}
		Date dataAux = adicionarDiasADataAtual(qtdDiaUteis,
				getDataAtualSemHoras());

		dataAux = verificaDiaDaSemana(dataAux);
		if (data.before(dataAux)) {
			return false;
		}
		return true;
	}

	private static Date verificaDiaDaSemana(Date data) {
		if (getDiaDaSemanaDoCalendar(data) == 7) {
			data = adicionarDiasADataAtual(Long.valueOf(2L), data);
		} else if (getDiaDaSemanaDoCalendar(data) == 1) {
			data = adicionarDiasADataAtual(Long.valueOf(1L), data);
		} else if (isFeriadoNacional(data)) {
			data = adicionarDiasADataAtual(Long.valueOf(1L), data);
			data = verificaDiaDaSemana(data);
		}
		return data;
	}

	public static boolean isFeriadoNacional(Date data) {
		int dia = 0;
		int mes = 0;
		int c = 0;
		int i = 0;
		int j = 0;
		int k = 0;
		int l = 0;
		int n = 0;

		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		int ano = cal.get(1);

		ArrayList<Date> feriados = new ArrayList(12);
		feriados.add(new GregorianCalendar(ano, 0, 1).getTime());
		feriados.add(new GregorianCalendar(ano, 3, 21).getTime());
		feriados.add(new GregorianCalendar(ano, 4, 1).getTime());
		feriados.add(new GregorianCalendar(ano, 8, 7).getTime());
		feriados.add(new GregorianCalendar(ano, 9, 12).getTime());
		feriados.add(new GregorianCalendar(ano, 10, 2).getTime());
		feriados.add(new GregorianCalendar(ano, 10, 15).getTime());
		feriados.add(new GregorianCalendar(ano, 11, 25).getTime());

		c = ano / 100;
		n = ano - 19 * (ano / 19);
		k = (c - 17) / 25;
		i = c - c / 4 - (c - k) / 3 + 19 * n + 15;
		i -= 30 * (i / 30);
		i -= i / 28 * (1 - i / 28) * (29 / (i + 1)) * ((21 - n) / 11);
		j = ano + ano / 4 + i + 2 - c + c / 4;
		j -= 7 * (j / 7);
		l = i - j;
		mes = 3 + (l + 40) / 44;
		dia = l + 28 - 31 * (mes / 4);

		Date dPascoa = new GregorianCalendar(ano, mes - 1, dia).getTime();

		feriados.add(dPascoa);

		cal.setTime(dPascoa);
		cal.add(5, -47);
		feriados.add(cal.getTime());

		cal.setTime(dPascoa);
		cal.add(5, -2);
		feriados.add(cal.getTime());

		cal.setTime(dPascoa);
		cal.add(5, 60);
		feriados.add(cal.getTime());

		return feriados.contains(data);
	}

	public static List countWeekendDays(int year, int month, int diaDaSemana) {
		List listaDatas = new ArrayList();
	    Calendar calendar = Calendar.getInstance();
	    // Note that month is 0-based in calendar, bizarrely.
	    calendar.set(year, month - 1, 1);
	    int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

	    int count = 0;
	    for (int day = 1; day <= daysInMonth; day++) {
	        calendar.set(year, month - 1, day);

	        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
	        if (dayOfWeek == diaDaSemana) {
	        	listaDatas.add(calendar(year, month, day));
	        }
	    }

	    return listaDatas;
	}

	public static List getDataCelulaReuniaoNaoRealizada(List<Calendar> datasCelulaReuniao, List<Calendar> datasAnoMesDiaReferencia) {
		List listaDatas = new ArrayList();
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");

		for (Calendar dataReferencia : datasAnoMesDiaReferencia) {
			boolean blnIncluirData = true;
			int dia = dataReferencia.get(Calendar.DAY_OF_MONTH);

			for (Calendar dataCelulaReuniao : datasCelulaReuniao) {
				int diaReferencia = dataCelulaReuniao.get(Calendar.DAY_OF_MONTH);
				if(dia <= diaReferencia)
					blnIncluirData = false;
			}

			if(blnIncluirData){
				listaDatas.add(format1.format(dataReferencia.getTime()));
			}
		}

	    return listaDatas;
	}

	public static List addDataCelulaReuniaoNaoRealizada(List<Calendar> datasAnoMesDiaReferencia) {
		List listaDatas = new ArrayList();
		SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");

		for (Calendar dataReferencia : datasAnoMesDiaReferencia) {
			listaDatas.add(format1.format(dataReferencia.getTime()));
		}

	    return listaDatas;
	}

	/**
	 * Compara duas datas utilizando apenas o Mês e o Ano como referencia.
	 *
	 * @param dataInicial Data para se comparar com a Data de Referencia.
	 * @param dataReferencia Data utilizada como referencia para se comparar o Mês e Ano.
	 * @return Retorna <code>-1</code> se <code>dataInicial</code> é menor que <code>dataReferencia</code>,
	 * retorna <code>0</code> se <code>dataInicial</code> for igual a <code>dataReferencia</code> e <code>1</code> se
	 * <code>dataInicial</code> for maior que a <code>dataReferencia</code>.
	 */
	public static int comparaMesAnoReferencia(Calendar dataInicial, Calendar dataReferencia) {
		if (dataInicial.get(Calendar.YEAR) < dataReferencia.get(Calendar.YEAR)) {
			return -1;
		} else if (dataInicial.get(Calendar.YEAR) > dataReferencia.get(Calendar.YEAR)) {
			return 1;
		} else {
			if (dataInicial.get(Calendar.MONTH) < dataReferencia.get(Calendar.MONTH)) {
				return -1;
			} else if (dataInicial.get(Calendar.MONTH) > dataReferencia.get(Calendar.MONTH)) {
				return 1;
			}
		}
		return 0;
	}

	/**
	 * Método responsável por verificar se a dataComparativa esta dentro da dataReferenciaVigente
	 *
	 * @author wilker.machado
	 *
	 * @param dataConclusao
	 * @param dataReferenciaVigente
	 * @return <code>boolean</code>
	 */
	public static boolean isDentroMes(Calendar dataComparativa, Calendar dataReferenciaVigente) {

		if (UtilObjeto.isReferencia(dataComparativa) && UtilObjeto.isReferencia(dataReferenciaVigente)) {

			return UtilDate.isMesIgual(dataComparativa, dataReferenciaVigente) && UtilDate.isAnoIgual(dataComparativa, dataReferenciaVigente);

		} else {

			return false;
		}

	}

	public static long convertTimeToMillis(final Calendar date) {
		return date.get(Calendar.HOUR_OF_DAY) * 3600000 + date.get(Calendar.MINUTE) * 60000 + date.get(Calendar.SECOND) * 1000 + date.get(Calendar.MILLISECOND);
	}

	public static void setTimeFromMillis(Calendar date, final long timeMillis) {
		int hour = new Double(timeMillis / 3600000).intValue();
		int minute = new Double((timeMillis - hour * 3600000) / 60000).intValue();
		int second = new Double((timeMillis - hour * 3600000 - minute * 60000) / 1000).intValue();
		int millis = new Double(timeMillis - hour * 3600000 - minute * 60000 - second * 1000).intValue();

		date.set(Calendar.HOUR_OF_DAY, hour);
		date.set(Calendar.MINUTE, minute);
		date.set(Calendar.SECOND, second);
		date.set(Calendar.MILLISECOND, millis);
	}

	public static String stringDateToJson(final String strDate) {
		Date date = stringToDate(strDate);

        return dateToJson(date);
	}

	public static String dateToJson(final Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        return dateFormat.format(date.getTime());
	}

	public static Date jsonToDate(final String value) {
		Date dataReturn = null;
		try {
			if (value.indexOf("T") < 0 || value.indexOf("/") > 0) {
				dataReturn = getData(value);
			}else{
				DateFormat data = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
				dataReturn = data.parse(value);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dataReturn;
	}

	public static Calendar ultimaDataAnovigente(Calendar dataVigente) {

		Calendar ultimaDataAnovigente = Calendar.getInstance();

		ultimaDataAnovigente.set(dataVigente.get(Calendar.YEAR), Calendar.DECEMBER, 31, 00, 00, 00);

		return ultimaDataAnovigente;
	}

	public static Calendar stringJsonToCalendar(String dataStr) {
		//2015-01-01T02:00:00.000Z
		dataStr = (String) dataStr.subSequence(0, 10);
		dataStr = dataStr.replace('-', '/');
		Calendar calendar = Calendar.getInstance();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			calendar.setTime(sdf.parse(dataStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return calendar;

	}

	public static Calendar primeiraDataAnoVigente(Calendar dataVigente) {

		Calendar primeiraDataAnoVigente = Calendar.getInstance();

		primeiraDataAnoVigente.set(dataVigente.get(Calendar.YEAR), Calendar.JANUARY, 1, 00, 00, 00);

		return primeiraDataAnoVigente;
	}

	public static Calendar getMesAtualComPrimeiroDiaHoraZerada(){

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);

		return cal;
	}

	public static String dataInicioFormatada(String data){
		String retorno = null;
		if(data != null){
			try{
				Date dt = new Date(data);
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
				retorno =  df.format(dt);
			}catch(BusinessException e){
				e.printStackTrace();
			}
		}
		return retorno;
	};

	public static String dataFimFormatada(String data){
		String retorno = null;
		if(data != null){
			try{
				Date dt = new Date(data);
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
				retorno =  df.format(dt);
			}catch(BusinessException e){
				e.printStackTrace();
			}
		}
		return retorno;
	};

}
