namespace todo_pwa.Models
{
    public class TodoTask
    {
        public int Id { get; set; }
        public string? Title { get; set; }
        public string? Description { get; set; }
        public string? Status { get; set; }
        public int? UserId { get; set; }
        public string? User { get; set; }
        public int? CategoryId { get; set; }
        public string? Category { get; set; }
    }
}
