package br.com.centralit.framework.json;


public class Views {

	public static class GenericView {}

	public static class LookupView extends Views.GenericView {}

	public static class ParceiroFindView {}

	public static class ParceiroAutoCompleteView extends Views.GenericView {}

	public static class FornecedorFindView {}

	public static class ContratoListView extends Views.GenericView {}

	public static class ContratoEditView extends Views.ContratoListView {}

	public static class UsuarioLogadoView {}

	public static class PaginaAjudaView extends Views.GenericView{}

	public static class UsuarioLogadoListView extends Views.GenericView{}

	public static class EstruturaOrganizacionalListView extends Views.GenericView {}

	public static class EstruturaOrganizacionalEditView extends Views.EstruturaOrganizacionalListView {}

	public static class EstruturaOrganizacionalAutoCompleteSimplesView extends Views.GenericView {}

	public static class MapaOrganizacionalListView extends GenericView{}

	public static class MapaOrganizacionalEditView extends MapaOrganizacionalListView{}

	public static class PessoaListViewListView extends GenericView{}

	public static class PessoaEditView extends PessoaListViewListView{}

	public static class ColaboradorAutoCompleteView extends Views.GenericView{}

	public static class ComissaoListView extends Views.GenericView{}

	public static class ComissaoEditView extends Views.ComissaoListView{}

	public static class CidadeListView extends GenericView{}

	public static class CidadeEditView extends CidadeListView{}

	public static class EnderecoListView extends BairroListView{}

	public static class EnderecoEditView extends EnderecoListView{}

	public static class DominioListView extends GenericView{}

	public static class DominioEditView extends DominioListView{}

	public static class PaisListView extends GenericView{}

	public static class PaisEditView extends PaisListView{}

	public static class RegiaoListView extends PaisListView{}

	public static class RegiaoEditView extends RegiaoListView{}

	public static class EstadoListView extends RegiaoListView{}

	public static class EstadoEditView extends EstadoListView{}

	public static class BairroListView extends EstadoListView{}

	public static class BairroEditView extends BairroListView{}

	public static class LocalizacaoListView extends GenericView{}

	public static class LocalizacaoAutoCompleteSimplesView extends GenericView{}

	public static class LocalizacaoEditView extends LocalizacaoListView{}

	public static class OrganizacaoAutoCompleteView extends GenericView{}

	public static class OrganizacaoListView extends OrganizacaoAutoCompleteView{}

	public static class OrganizacaoEditView extends OrganizacaoListView{}

	public static class CaracteristicaListView extends GenericView{}

	public static class CaracteristicaEditView extends CaracteristicaListView{}

	public static class MenuListView extends GenericView{}

	public static class MenuEditView extends MenuListView{}

	public static class MenuListChildrenView extends MenuListView{}

	public static class MenuListFrontEndView extends MenuListView{}

	public static class MenuListSearchFrontEndView extends MenuListView{}

	public static class MenuFileListView extends GenericView{}

	public static class MenuFileEditView extends MenuFileListView{}

	public static class HistoricoBemPatrimonialTimelineView extends GenericView {}

	public static class UsuarioListView extends GenericView{}

	public static class UsuarioEditView extends UsuarioListView{}

	public static class ConfiguracaoView extends GenericView{}

	public static class GrupoListView extends GenericView{}

	public static class GrupoEditView extends GrupoListView{}

	public static class GrupoAutoCompleteView extends GenericView{};

	public static class PainelListView extends GenericView{}

	public static class PainelEditView extends PainelListView{}

	public static class WidgetListView extends GenericView{}

	public static class WidgetEditView extends WidgetListView{}

	public static class WidgetItemListView extends GenericView{}

	public static class WidgetItemEditView extends WidgetItemListView{}

	public static class InternacionalizacaoListView extends GenericView{}

	public static class InternacionalizacaoEditView extends InternacionalizacaoListView{}

	public static class TelaJsonView {}

	public static class QuestionarioAutoCompleteView extends GenericView{}

	public static class QuestionarioListView extends GenericView{}

	public static class QuestionarioEditView extends QuestionarioListView{}

	public static class ModeloEmailAutoCompleteView extends GenericView{}

	public static class FluxoTrabalhoAutoCompleteView extends GenericView{}

	public static class ProcessoNegocioAutoCompleteView extends GenericView{}

	public static class ColaboradorContratoAutoCompleteView{}

	public static class SeguradoraAutoCompleteView extends GenericView {}

	public static class CalendarListView extends GenericView{}

	public static class CalendarEditView extends CalendarListView{}

	public static class DashBoardListView extends GenericView{}

	public static class BusinessProcessListView extends GenericView{}

	public static class BusinessProcessEditView extends BusinessProcessListView{}

	public static class EstruturaOrganizacionalAutoCompleteView extends Views.GenericView {}

	public static class EstruturaOrganizacionalSimplesView extends Views.GenericView {}

	public static class BreadcrumbView {}

	public static class NotificacaoListView extends GenericView {};

	public static class NotificacaoEditView extends NotificacaoListView {};

	public static class NotificacaoAutoCompleteView extends GenericView {};

	public static class ServicoListView extends GenericView{}

	public static class ServicoEditView extends ServicoListView{}

	public static class EquipeListView  extends Views.GenericView{}

	public static class EquipeEditView  extends Views.EquipeListView{}

	public static class FuncionarioAutoCompleteView extends Views.GenericView{}

	public static class ServicoAutoCompleteView extends Views.GenericView{}

	public static class LuckListView extends GenericView{}

	public static class LuckEditView extends LuckListView{}

	public static class CaixaListView extends GenericView{}

	public static class CaixaEditView extends CaixaListView{}


}